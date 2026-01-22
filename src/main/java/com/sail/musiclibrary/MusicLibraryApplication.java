package com.sail.musiclibrary;

import com.sail.musiclibrary.artist.ArtistService;
import com.sail.musiclibrary.media.album.Album;
import com.sail.musiclibrary.artist.ArtistProfile;
import com.sail.musiclibrary.media.album.AlbumService;
import com.sail.musiclibrary.media.album.song.Song;
import com.sail.musiclibrary.media.album.song.SongService;
import com.sail.musiclibrary.playlist.Playlist;
import com.sail.musiclibrary.playlist.PlaylistService;
import com.sail.musiclibrary.user.User;
import com.sail.musiclibrary.media.MediaService;
import com.sail.musiclibrary.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MusicLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicLibraryApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(UserService userService, SongService songService, AlbumService albumService, ArtistService artistService, PlaylistService playlistService) {
        return args -> {
            System.out.println("--- STARTING SIMULATION ---");

            User u1 = userService.createUser("screwdriver52");
            User u2 = userService.createUser("busy_artist");
            User u3 = userService.createUser("amogus67");
            User u4 = userService.createUser("lazy_artist");

            ArtistProfile u2ap = artistService.promote(u2.getId(), "I produce best music");
            ArtistProfile u4ap = artistService.promote(u4.getId(), "Cozy music for you");

            Album a1 = albumService.releaseAlbum(u2.getId(), "RockRulez");
            Song s1 = songService.uploadSong(a1, "RnR", 180);
            Song s2 = songService.uploadSong(a1, "Dead End", 240);

            Album a2 = albumService.releaseAlbum(u4.getId(), "CozyFi");
            Song s3 = songService.uploadSong(a2, "Lazy Town", 99);
            Song s4 = songService.uploadSong(a2, "Cute Paradigm", 172);

            try {
                Album a3 = albumService.releaseAlbum(u3.getId(), "I wanna release an album");
            } catch (IllegalStateException ise) {
                System.out.println(ise.getMessage());
            }

            Playlist p1 = playlistService.createPlaylist("Rock playlist", u1);
            playlistService.addSong(p1.getId(), s1.getId());
            playlistService.addSong(p1.getId(), s2.getId());

            Playlist p2 = playlistService.createPlaylist("My favs", u3);
            playlistService.addSong(p2.getId(), s3.getId());
            playlistService.addSong(p2.getId(), s2.getId());

            playlistService.renamePlaylist(p2.getId(), "My Favourites", u3.getId());

            songService.deleteSong(s3.getId(), u4.getId());

            s1.play();

            System.out.println("--- SIMULATION COMPLETE ---");
        };
    }
}
