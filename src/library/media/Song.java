package library.media;

import library.playlist.Album;

public class Song extends Media implements Player {
    // Fields
    private String genre;
    private final Album album;

    // Constructors
    public Song(String title, int duration, Album album) {
        super(title, duration);
        this.album = album;
        album.addSong(this);
    }

    // Getters
    public String getGenre() {
        return genre;
    }

    public Album getAlbum() {
        return album;
    }

    // Setters
    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Song{title='" + getTitle() +
                "', duration=" + getDuration() +
                ", genre='" + genre +
                "', album='" + album.getName() +
                "'}";
    }

    @Override
    public void play() {
        System.out.println("Playing song " + getTitle());
    }
}