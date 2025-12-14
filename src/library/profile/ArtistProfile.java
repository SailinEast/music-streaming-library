package library.profile;

import library.playlist.Album;
import library.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArtistProfile {
    // Fields
    private final User user;
    private List<Album> albums;
    private String bio;

    // Does not have no-arg constructor but 1 parametrized constructor
    public ArtistProfile(User user) {
        this.user = user;
        albums = new ArrayList<>();
    }

    // Getters
    public User getUser() {
        return user;
    }

    public List<Album> getAlbums() {
        return Collections.unmodifiableList(albums);
    }

    // Setters
    public void setBio(String bio) {
        this.bio = bio;
    }

    // Other methods
    public void createAlbum(String title) { // Artist's profile creates albums
        Album album = new Album(title, this);
        albums.add(album);
    }

    @Override
    public String toString() {
        return "ArtistProfile{handle='" + user.getHandle() +
                "', bio='" + bio +
                "'}";
    }
}
