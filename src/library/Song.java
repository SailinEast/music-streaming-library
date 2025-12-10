package library;

public class Song {
    // Fields
    private String title;
    private final Artist artist;

    // 1 big parametrized constructor
    public Song(String title, Artist artist) {
        this.title = title;
        this.artist = artist;
        artist.addSong(this); // adds a song object to artist's song list when it's initialized
    }

    // Getters
    public Artist getArtist() {
        return artist;
    }
    public String getTitle() {
        return title;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }
}