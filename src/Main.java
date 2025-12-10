import library.Artist;
import library.Playlist;
import library.Song;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Some objects' initialization
        Playlist playlist1 = new Playlist("My epic playlist", "TurboMan228");
        Playlist playlist2 = new Playlist("Christmas vibes"); // this one has no creator, add User class later so each playlist has a creator
        Playlist playlist3 = new Playlist("TestBlank", "TurboMan22");

        Artist artist1 = new Artist("Wham!");
        Artist artist2 = new Artist("Lil Big Stack");
        Artist artist3 = new Artist("Mariah Carey");
        Artist artist4 = new Artist("Unknown"); // this is to test song.setTitle() method

        Song song1 = new Song("Last Christmas", artist1);
        Song song2 = new Song("Everything She Wants", artist1);
        Song song3 = new Song("Labubu Dubai Chocolate", artist2);
        Song song4 = new Song("All I Want For Christmas Is You", artist3);
        Song song5 = new Song("An unknown song", artist4); // song.setTitle() test

        playlist1.addSong(song1);
        playlist1.addSong(song3);
        playlist2.addSong(song1);
        playlist2.addSong(song4);

        // Gets artist1's name through song1
        print(song1.getArtist().getArtistName());
        // Gets creator of playlist2 which is null
        print(playlist2.getCreator());
        // Get playlist2 ID
        print("playlist2 ID: " + playlist2.getPlaylistId());

        print(""); // new line

        // Lists songs that are in playlist1
        printSongList(playlist1.getSongs());
        // Removes song from playlist1
        playlist1.rmSong(song1);
        // After removal
        printSongList(playlist1.getSongs());

        print("");

        // Lists songs made by artist1
        printSongList(artist1.getSongs());

        print("");

        // Changing song's title also changes it in artist's list of songs
        song5.setTitle("A known song");
        print("song5 title: " + song5.getTitle());
        print("artist4 song list title: " + artist4.getSongs().getFirst().getTitle());

        print("");

        // Get playlist2 name
        print("Before: " + playlist2.getPlaylistName());
        // Set a new name for playlist2
        playlist2.setPlaylistName("New year vibes");
        // Check name again
        print("After: " + playlist2.getPlaylistName());
    }

    // Helper methods
    public static void print(Object obj) {
        System.out.println(obj);
    }

    public static void printSongList(List<Song> songs) {
        for (int i = 0; i < songs.size(); i++) {
            System.out.print(songs.get(i).getTitle());
            if (i < songs.size() - 1) {
                System.out.print(", ");
            }
        }
        print("");
    }
}