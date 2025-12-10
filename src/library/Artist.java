package library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Artist {
    // Fields
    private final String artistName;
    private final List<Song> songs = new ArrayList<>(); // list of artist's songs

    // Does not have no-arg constructor but 1 parametrized constructor
    public Artist(String artistName) {
        this.artistName = artistName;
    }

    // Getters
    public String getArtistName() {
        return artistName;
    }
    public List<Song> getSongs() {
        return Collections.unmodifiableList(songs);
    }

    // Other methods
    void addSong(Song song) { // a package-private method so whenever you create a new song object
        songs.add(song);      // it automatically adds a song to the list of artist's songs
    }
}
