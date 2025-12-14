package library.playlist;

import library.media.Song;
import library.profile.ArtistProfile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Album {
    private String name;
    private final ArtistProfile artist;
    private List<Song> songs = new ArrayList<>();

    public Album(String name, ArtistProfile artist) {
        this.name = Objects.requireNonNull(name, "Album name cannot be null!");
        this.artist = artist;
    }

    // Getters
    public String getName() {
        return name;
    }

    public ArtistProfile getArtist() {
        return artist;
    }

    public List<Song> getSongs() {
        return Collections.unmodifiableList(songs);
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    // Methods
    public void addSong(Song song) {
        songs.add(song);
    }

    public int albumDuration() {
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
        return "Album{name='" + name +
                "', artistHandle='" + artist.getUser().getHandle() +
                "', songs=" + songTitles +
                "}";
    }
}
