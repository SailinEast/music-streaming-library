package com.sail.musiclibrary.media.album.song;

import com.sail.musiclibrary.common.Media;
import com.sail.musiclibrary.playlist.Playlist;
import com.sail.musiclibrary.media.album.Album;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "songs")
@NoArgsConstructor
public class Song extends Media {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter private Long id;

    @ManyToOne
    @JoinColumn(name = "album_id")
    @Getter private Album album;

    @ManyToMany(mappedBy = "songs")
    @Getter private List<Playlist> playlists;

    public Song(String title, int duration, Album album) {
        this.title = title;
        this.duration = duration;
        this.album = album;
    }

    @Override
    public String toString() {
        return String.format("Song{title=%s, durationSeconds=%d}", title, duration);
    }

    @Override
    public void play() {
        System.out.println("Playing song: " + title);
    }
}
