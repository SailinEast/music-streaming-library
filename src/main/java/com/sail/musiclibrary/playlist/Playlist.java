package com.sail.musiclibrary.playlist;

import com.sail.musiclibrary.media.album.song.Song;
import com.sail.musiclibrary.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Playlist {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter private Long id;

    @Getter private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter private User owner;

    @ManyToMany
    @JoinTable(
            name = "playlist_song",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    @Getter private List<Song> songs = new ArrayList<>();

    public Playlist(String name, User owner) {
        this.name = name;
        this.owner = owner;
    }

    public void rename(String newName) {
        name = newName;
    }
}
