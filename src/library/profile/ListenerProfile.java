package library.profile;

import library.media.Player;
import library.playlist.Playlist;
import library.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ListenerProfile {
    // Fields
    private final User user;
    private List<Playlist> playlists;

    // Constructor
    public ListenerProfile(User user) {
        this.user = user;
        playlists = new ArrayList<>();
    }

    // Getters
    public User getUser() {
        return user;
    }

    public List<Playlist> getPlaylists() {
        return Collections.unmodifiableList(playlists);
    }

    // Methods
    public void createPlaylist(String playlistName) {
        for (Playlist playlist : playlists) {
            if (Objects.equals(playlist.getName(), playlistName)) {
                System.out.println("Can't create same playlist names under the same user");
                return;
            }
        }
        Playlist playlist = new Playlist(playlistName, this);
        playlists.add(playlist);
    }

    public void listen(Player player) {
        player.play();
    }

    @Override
    public String toString() {
        String playlistNames = playlists.stream().map(p -> "'" + p.getName() + "'")
                .collect(Collectors.joining(", ", "[", "]"));
        return "ListenerProfile{handle='" + user.getHandle() +
                "', playlists=" + playlistNames +
                "}";
    }
}
