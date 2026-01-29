package com.sail.musiclibrary.media.album;

import com.sail.musiclibrary.artist.ArtistProfile;
import com.sail.musiclibrary.common.BaseService;
import com.sail.musiclibrary.common.dto.album.AlbumResponse;
import com.sail.musiclibrary.media.album.song.SongService;
import com.sail.musiclibrary.user.User;
import com.sail.musiclibrary.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AlbumService extends BaseService<Album, Long> {
    private final AlbumRepository albumRepository;
    private final UserService userService;
    private final SongService songService;

    @Transactional
    public AlbumResponse releaseAlbum(Long userId, String title, Long requesterId) {
        User user = validateUser(userId, requesterId);

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
        Album album = validateAlbumOwner(albumId, requesterId);

        songService.deleteAllFromAlbum(albumId);
        albumRepository.delete(album);
    }

    @Transactional
    public AlbumResponse renameAlbum(Long albumId, String newTitle, Long requesterId) {
        Album album = validateAlbumOwner(albumId, requesterId);
        album.rename(newTitle);
        albumRepository.save(album);
        return new AlbumResponse(
            albumId,
            newTitle,
            requesterId,
            Collections.emptyList()
        );
    }

    public User validateUser(Long userId, Long requesterId) {
        User user = userService.findById(userId);
        if (!user.isArtist()) {
            throw new IllegalStateException("User '" + user.getHandle() + "' is not an artist!");
        } else if (!Objects.equals(userId, requesterId)) {
            throw new SecurityException("Access Denied");
        } else {
            return user;
        }
    }

    public Album validateAlbumOwner(Long albumId, Long requesterId) {
        Album album = this.findById(albumId);
        ArtistProfile artist = userService.findById(requesterId).getArtistProfile();
        if (!Objects.equals(album.getArtist(), artist)) {
            throw new SecurityException("Access Denied");
        }
        return album;
    }

    @Override
    protected JpaRepository<Album, Long> getRepository() {
        return albumRepository;
    }
}
