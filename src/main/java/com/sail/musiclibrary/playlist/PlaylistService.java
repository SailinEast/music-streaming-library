package com.sail.musiclibrary.playlist;

import com.sail.musiclibrary.common.BaseService;
import com.sail.musiclibrary.media.album.song.Song;
import com.sail.musiclibrary.media.album.song.SongService;
import com.sail.musiclibrary.user.User;
import com.sail.musiclibrary.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaylistService extends BaseService<Playlist, Long> {
    private final PlaylistRepository playlistRepository;
    private final SongService songService;
    private final UserService userService;

    // Playlist manipulations
    @Transactional
    public Playlist createPlaylist(String name, Long ownerId) {
        User user = userService.findById(ownerId);
        Playlist playlist = new Playlist(name, user);
        return playlistRepository.save(playlist);
    }

    @Transactional
    public void deletePlaylist(Long playlistId, Long requesterId) {
        Playlist playlist = verifyUser(playlistId, requesterId);

        playlistRepository.delete(playlist);
    }

    @Transactional
    public Playlist renamePlaylist(Long playlistId, String newName, Long requesterId) {
        Playlist playlist = verifyUser(playlistId, requesterId);

        playlist.rename(newName);
        return playlistRepository.save(playlist);
    }

    // Song manipulations
    @Transactional
    public void addSongToPlaylist(Long playlistId, Long songId, Long requesterId) {
        Playlist playlist = verifyUser(playlistId, requesterId);
        Song song = songService.findById(songId);

        playlist.addSong(song);
    }

    @Transactional
    public void removeSongFromPlaylist(Long playlistId, Long songId, Long requesterId) {
        Playlist playlist = verifyUser(playlistId, requesterId);
        Song song = songService.findById(songId);

        playlist.removeSong(song);
    }

    private Playlist verifyUser(Long playlistId, Long requesterId) {
        Playlist playlist = this.findById(playlistId);

        // if playlist owner id and requester id not equals, throw an exception
        // else return playlist object
        if (!playlist.getOwner().getId().equals(requesterId)) {
            throw new SecurityException("Access Denied");
        } else {
            return playlist;
        }
    }

    @Override
    protected JpaRepository<Playlist, Long> getRepository() {
        return playlistRepository;
    }
}
