package library;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Playlist {
    // Fields
    private String playlistName;
    private static int id_gen = 0;
    private final int playlistId;
    private final List<Song> songs = new ArrayList<>(); // a list containing Song objects
    private String creator;

    // No-arg constructor
    public Playlist() {
        playlistId = id_gen;
        id_gen++;
    }

    // Parametrized constructor
    public Playlist(String playlistName) {
        this();
        this.playlistName = playlistName;
        creator = null;
    }

    // Another parametrized constructor
    public Playlist(String playlistName, String creator) {
        this(playlistName); // reference to the previous constructor
        this.creator = creator;
    }

    // Getters
    public String getCreator() {
        if (this.creator != null) return creator;
        else return "Creator does not exist";
    }
    public int getPlaylistId() {
        return playlistId;
    }
    public String getPlaylistName() {
        return playlistName;
    }
    public List<Song> getSongs() {
        return Collections.unmodifiableList(songs);
    }

    // Setters
    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    // Other methods
    public void addSong(Song song) {
        songs.add(song);
    }
    public void rmSong(Song song) {
        songs.remove(song);
    }
}
