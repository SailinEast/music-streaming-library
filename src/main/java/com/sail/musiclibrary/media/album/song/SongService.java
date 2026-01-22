package com.sail.musiclibrary.media.album.song;

import com.sail.musiclibrary.artist.ArtistProfile;
import com.sail.musiclibrary.common.BaseService;
import com.sail.musiclibrary.media.album.Album;
import com.sail.musiclibrary.media.album.AlbumService;
import com.sail.musiclibrary.user.User;
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


        Song song = new Song(title, duration, albumService.findById(albumId));
        return songRepository.save(song);
    }

    @Transactional
    public void deleteSong(Long songId, Long requesterId) {
        Song song = this.findById(songId);

        validateOwnership(songId, requesterId);

        song.getPlaylists().forEach(playlist -> playlist.getSongs().remove(song));
        songRepository.delete(song);
    }

    private void validateOwnership(Long songId, Long userId) {
        Album album = songRepository.findById(songId).orElseThrow().getAlbum();
        if (album == null) return;
        ArtistProfile artist = album.getArtist();
        User owner = artist.getUser();
        if (!artist.getUser().getId().equals(userId)) throw new SecurityException("Access Denied");
    }

    @Override
    protected JpaRepository<Song, Long> getRepository() {
        return songRepository;
    }
}
