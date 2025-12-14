import library.manager.AlbumManager;
import library.manager.MediaManager;
import library.manager.PlaylistManager;
import library.manager.UserManager;
import library.media.Podcast;
import library.playlist.Album;
import library.profile.ArtistProfile;
import library.playlist.Playlist;
import library.media.Song;
import library.profile.HostProfile;
import library.profile.ListenerProfile;
import library.user.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Managers' initialization for sorting/filtering/searching
        UserManager userManager = new UserManager();
        PlaylistManager playlistManager = new PlaylistManager();
        MediaManager mediaManager = new MediaManager();
        AlbumManager albumManager = new AlbumManager();

        // Create users
        User user1 = new User("MagicMan", "knight");
        User user2 = new User("Bu11s3y3");
        User user3 = new User("musician", "Best Artist");
        User user4 = new User("UnicornHunter", "Knight");
        User user5 = new User("null");
        User user6 = new User("meh", "KNIGHT");
        User user7 = new User("MagicMan");

        // Apply host and artist roles
        user2.promoteToHost();
        user3.promoteToArtist();
        user6.promoteToHost();
        user6.promoteToArtist();

        // Users' profiles according to their access level
        ListenerProfile user1profile = user1.getListenerProfile();
        ListenerProfile user2profile = user2.getListenerProfile();
        ListenerProfile user3profile = user3.getListenerProfile();
        ListenerProfile user4profile = user4.getListenerProfile();
        ListenerProfile user5profile = user5.getListenerProfile();
        ListenerProfile user6profile = user6.getListenerProfile();
        // user7 doesn't exist (same handles)

        HostProfile user2hostProfile = user2.getHostProfile();
        HostProfile user6hostProfile = user6.getHostProfile();
        ArtistProfile user3artistProfile = user3.getArtistProfile();
        ArtistProfile user6artistProfile = user6.getArtistProfile();

        // Podcasts
        Podcast podcast1 = new Podcast("Discussing", 600, user2hostProfile);
        Podcast podcast2 = new Podcast("Space exploration", 958, user2hostProfile);
        podcast1.setDescription("Discussing about politics");
        podcast2.setDescription("The amazing universe of space");

        print(podcast1.toString());

        // Albums and songs initialization
        Album album1 = new Album("Epic Album", user3artistProfile);
        Album album2 = new Album("Stinky Album", user3artistProfile);
        Song song1 = new Song("Best song", 180, album1);
        Song song2 = new Song("2nd best song", 190, album1);
        Song song3 = new Song("My worst song", 67, album2);

        // Listeners can create playlists
        Playlist playlist1 = new Playlist("Favourites", user1profile);
        Playlist playlist2 = new Playlist("year 2067 hit songs", user4profile);
        playlist1.addSong(song2);
        playlist2.addSong(song3);
        playlist2.addSong(song1);

        // Output
        print(album1.toString());
        print(user1profile.toString());
        print(playlist1.toString());

        // Manager registrations
        userManager.registerUser(user1);
        userManager.registerUser(user2);
        userManager.registerUser(user3);
        userManager.registerUser(user4);
        userManager.registerUser(user5);
        userManager.registerUser(user6);
        // userManager.registerUser(user7);  // returns error because of repeated handles
        albumManager.registerAlbum(album1);
        albumManager.registerAlbum(album2);
        mediaManager.registerSong(song1);
        mediaManager.registerSong(song2);
        mediaManager.registerSong(song3);
        mediaManager.registerPodcast(podcast1);
        mediaManager.registerPodcast(podcast2);
        playlistManager.registerPlaylist(playlist1);
        playlistManager.registerPlaylist(playlist2);

        // Searching in users
        print(userManager.findUsersByDisplayName("knight"));

        // Sorting
        List<User> sortedUsers = userManager.sortUserHandlesAlphabetical();
        sortedUsers.forEach(System.out::println);

        // Action
        user1profile.listen(song2);
        user5profile.listen(podcast1);
    }

    // Helper methods
    public static void print(Object obj) {
        System.out.println(obj);
    }
}