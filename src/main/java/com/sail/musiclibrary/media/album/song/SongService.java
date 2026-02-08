package com.sail.musiclibrary.media.album.song;

import com.sail.musiclibrary.access.AlbumOwnershipPolicy;
import com.sail.musiclibrary.common.BaseService;
import com.sail.musiclibrary.common.dto.song.SongResponse;
import com.sail.musiclibrary.media.album.Album;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongService extends BaseService<Song, Long> {
    private final SongRepository songRepository;
    private final AlbumOwnershipPolicy albumOwnershipPolicy;

    @Transactional
    public SongResponse uploadSong(Long albumId, String title, int duration, Long requesterId) {
        Album album = albumOwnershipPolicy.validateOwner(albumId, requesterId);
        Song song = new Song(title, duration, album);
        songRepository.save(song);
        return new SongResponse(
            song.getId(),
            title,
            albumId,
            requesterId,
            duration
        );
    }

    @Transactional
    public void deleteSong(Long songId, Long requesterId) {
        Long albumId = this.findById(songId).getAlbum().getId();
        albumOwnershipPolicy.validateOwner(albumId, requesterId);

        Song song = this.findById(songId);

        songRepository.removeSongFromAllPlaylists(songId);

        songRepository.delete(song);
    }

    @Transactional
    public SongResponse renameSong(Long songId, String newTitle, Long requesterId) {
        Long albumId = this.findById(songId).getAlbum().getId();
        albumOwnershipPolicy.validateOwner(albumId, requesterId);

        Song song = this.findById(songId);
        song.rename(newTitle);

        songRepository.save(song);

        return new SongResponse(
            songId,
            newTitle,
            albumId,
            requesterId,
            song.getDuration()
        );
    }

    @Transactional
    public void deleteAllFromAlbum(Long albumId) {
        // TODO: Delete actual .mp3 files from drive later on

        songRepository.removeSongsFromPlaylistsByAlbums(albumId);
        songRepository.deleteAllByAlbumId(albumId);
    }

    @Override
    protected JpaRepository<Song, Long> getRepository() {
        return songRepository;
    }
}
