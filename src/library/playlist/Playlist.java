package library.playlist;

import library.media.Song;
import library.profile.ListenerProfile;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class Playlist {
    // Fields
    private String name;
    private final List<Song> songs = new ArrayList<>(); // a list containing Song objects
    private final ListenerProfile creator;

    // Constructor
    public Playlist(String name, ListenerProfile creator) {
        this.name = Objects.requireNonNull(name, "Playlist name cannot be null!");
        this.creator = creator;
    }

    // Getters
    public ListenerProfile getCreator() {
        return creator;
    }
    public String getName() {
        return name;
    }
    public List<Song> getSongs() {
        return Collections.unmodifiableList(songs);
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    // Other methods
    public void addSong(Song song) {
        songs.add(song);
    }
    public void rmSong(Song song) {
        songs.remove(song);
    }
    public int playlistDuration() {
        int sum = 0;
        for (Song song : songs) {
            sum += song.getDuration();
        }
        return sum;
    }

    @Override
    public String toString() {
        String songTitles = songs.stream().map(s -> "'" + s.getTitle() + "'")
                .collect(Collectors.joining(", ", "[", "]"));
        return "Playlist{name='" + name +
                "', creatorHandle='" + creator.getUser().getHandle() +
                "', songs=" + songTitles +
                "}";
    }
}
