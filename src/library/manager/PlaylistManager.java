package library.manager;

import library.playlist.Playlist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlaylistManager {
    private List<Playlist> playlists = new ArrayList<>();

    public List<Playlist> getPlaylists() {
        return Collections.unmodifiableList(playlists);
    }

    public void registerPlaylist(Playlist playlist) {
        playlists.add(playlist);
    }

    public void rmPlaylist(Playlist playlist) {
        playlists.remove(playlist);
    }

    public List<Playlist> findPlaylistsByName(String name) {
        return playlists.stream().filter(p ->
                p.getName().trim().equalsIgnoreCase(name)).toList();
    }
}
