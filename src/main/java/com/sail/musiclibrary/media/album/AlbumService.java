package com.sail.musiclibrary.media.album;

import com.sail.musiclibrary.access.ArtistAccessValidator;
import com.sail.musiclibrary.access.AlbumOwnershipPolicy;
import com.sail.musiclibrary.common.BaseService;
import com.sail.musiclibrary.common.dto.album.AlbumResponse;
import com.sail.musiclibrary.media.album.song.SongService;
import com.sail.musiclibrary.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AlbumService extends BaseService<Album, Long>{
    private final AlbumRepository albumRepository;
    private final SongService songService;
    private final AlbumOwnershipPolicy ownershipPolicy;
    private final ArtistAccessValidator accessValidator;

    @Transactional
    public AlbumResponse releaseAlbum(Long userId, String title, Long requesterId) {
        User user = accessValidator.validate(userId, requesterId);

        Album album = new Album(title, user.getArtistProfile());
        albumRepository.save(album);

        return new AlbumResponse(
            album.getId(),
            title,
            requesterId,
            Collections.emptyList()
        );
    }

    @Transactional
    public void deleteAlbum(Long albumId, Long requesterId) {
        Album album = ownershipPolicy.validateOwner(albumId, requesterId);

        songService.deleteAllFromAlbum(albumId);
        albumRepository.delete(album);
    }

    @Transactional
    public AlbumResponse renameAlbum(Long albumId, String newTitle, Long requesterId) {
        Album album = ownershipPolicy.validateOwner(albumId, requesterId);
        album.rename(newTitle);
        albumRepository.save(album);
        return new AlbumResponse(
            albumId,
            newTitle,
            requesterId,
            Collections.emptyList()
        );
    }

    @Override
    protected JpaRepository<Album, Long> getRepository() {
        return albumRepository;
    }
}
