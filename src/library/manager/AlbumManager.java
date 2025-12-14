package library.manager;

import library.media.Podcast;
import library.playlist.Album;
import library.playlist.Playlist;
import library.media.Song;
import library.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlbumManager {
    private List<Album> albums = new ArrayList<>();

    public List<Album> getAlbums() {
        return Collections.unmodifiableList(albums);
    }

    public void registerAlbum(Album album) {
        albums.add(album);
    }

    public void rmAlbum(Album album) {
        albums.remove(album);
    }

    public List<Album> findAlbumsByArtistName(String artistName) {
        return albums.stream().filter(a ->
                a.getArtist().getUser().getHandle().trim().equalsIgnoreCase(artistName.trim())).toList();
    }
}
