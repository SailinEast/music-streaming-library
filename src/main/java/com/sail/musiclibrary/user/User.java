package com.sail.musiclibrary.user;

import com.sail.musiclibrary.artist.ArtistProfile;
import com.sail.musiclibrary.playlist.Playlist;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter private Long id;

    @Getter private String handle;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Playlist> playlists;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @Getter @Setter private ArtistProfile artistProfile;

    public List<Playlist> getPlaylists() {
        return Collections.unmodifiableList(playlists);
    }

    public User(String handle) {
        this.handle = handle;
    }

    public boolean isArtist() {
        return artistProfile != null;
    }
}
