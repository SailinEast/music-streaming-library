package library.manager;

import library.media.Podcast;
import library.media.Song;
import library.playlist.Playlist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MediaManager {
    private List<Song> songs = new ArrayList<>();
    private List<Podcast> podcasts = new ArrayList<>();

    public List<Song> getSongs() {
        return Collections.unmodifiableList(songs);
    }

    public List<Podcast> getPodcasts() {
        return Collections.unmodifiableList(podcasts);
    }

    public void registerSong(Song song) {
        songs.add(song);
    }

    public void registerPodcast(Podcast podcast) {
        podcasts.add(podcast);
    }

    public void rmSong(Song song) {
        songs.remove(song);
    }

    public void rmPodcast(Podcast podcast) {
        podcasts.remove(podcast);
    }

    public List<Song> findSongsByTitle(String title) {
        return songs.stream().filter(s -> s.getTitle().equalsIgnoreCase(title)).toList();
    }

    public List<Podcast> findPodcastsByHost(String host) {
        return podcasts.stream().filter(p ->
                p.getHost().getUser().getHandle().trim().equalsIgnoreCase(host)).toList();
    }
}
