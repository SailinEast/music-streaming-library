package com.sail.musiclibrary.media.album.song;

import com.sail.musiclibrary.common.BaseService;
import com.sail.musiclibrary.media.album.Album;
import com.sail.musiclibrary.media.album.AlbumService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongService extends BaseService<Song, Long> {
    private final SongRepository songRepository;
    private final AlbumService albumService;

    @Transactional
    public Song uploadSong(Long albumId, String title, int duration, Long requesterId) {
        Album album = validateOwnership(albumId, requesterId);
        Song song = new Song(title, duration, album);
        return songRepository.save(song);
    }

    @Transactional
    public void deleteSong(Long songId, Long requesterId) {
        Long albumId = this.findById(songId).getAlbum().getId();
        validateOwnership(albumId, requesterId);

        Song song = this.findById(songId);

        songRepository.removeSongFromAllPlaylists(songId);

        songRepository.delete(song);
    }

    @Transactional
    public Song renameSong(Long songId, String newTitle, Long requesterId) {
        Long albumId = this.findById(songId).getAlbum().getId();
        validateOwnership(albumId, requesterId);

        Song song = this.findById(songId);
        song.rename(newTitle);

        return songRepository.save(song);
    }

    private Album validateOwnership(Long albumId, Long requesterId) {
        // if userId and album's owner id equals
        // return album object
        Album album = albumService.findById(albumId);
        if (!album.getArtist().getUser().getId().equals(requesterId))
            throw new SecurityException("Access Denied");
        else return album;
    }

    @Override
    protected JpaRepository<Song, Long> getRepository() {
        return songRepository;
    }
}
