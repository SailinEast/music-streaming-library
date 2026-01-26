package com.sail.musiclibrary.media.album;

import com.sail.musiclibrary.artist.ArtistProfile;
import com.sail.musiclibrary.media.album.song.Song;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
public class Album {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter private Long id;

    @Getter private String title;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    @Getter private ArtistProfile artist;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private List<Song> songs;

    public Album() {}
    public Album(String title, ArtistProfile artist) {
        this.title = title;
        this.artist = artist;
    }
}
