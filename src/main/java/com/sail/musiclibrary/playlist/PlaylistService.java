package com.sail.musiclibrary.playlist;

import com.sail.musiclibrary.common.BaseService;
import com.sail.musiclibrary.media.MediaService;
import com.sail.musiclibrary.media.album.song.Song;
import com.sail.musiclibrary.media.album.song.SongService;
import com.sail.musiclibrary.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaylistService extends BaseService<Playlist, Long> {
    private final PlaylistRepository playlistRepository;
    private final SongService songService;

    // Playlist manipulations
    @Transactional
    public Playlist createPlaylist(String name, User owner) {
        Playlist playlist = new Playlist(name, owner);
        return playlistRepository.save(playlist);
    }

    @Transactional
    public void deletePlaylist(Long id) {
        playlistRepository.deleteById(id);
    }

    @Transactional
    public void renamePlaylist(Long id, String newName, Long requesterId) {
        Playlist playlist = this.findById(id);
        if (!playlist.getOwner().getId().equals(requesterId)) {
            System.out.println("Access Denied");
        }
        playlist.rename(newName);
        playlistRepository.save(playlist);
    }

    // Song manipulations
    @Transactional
    public void addSong(Long playlistId, Long songId) {
        Song song = songService.findById(songId);
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow();

        playlist.getSongs().add(song);
    }

    @Transactional
    public void removeSong(Long playlistId, Long songId) {
        Song song = songService.findById(songId);
        Playlist playlist = this.findById(playlistId);

        playlist.getSongs().remove(song);
    }

    @Override
    protected JpaRepository<Playlist, Long> getRepository() {
        return playlistRepository;
    }
}
