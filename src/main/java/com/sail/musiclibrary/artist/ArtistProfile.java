package com.sail.musiclibrary.artist;

import com.sail.musiclibrary.media.album.Album;
import com.sail.musiclibrary.user.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
public class ArtistProfile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter private String bio;

    @OneToOne
    @JoinColumn(name = "user_id")
    @Getter private User user;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    @Getter private List<Album> albums;

    public ArtistProfile() {}
    public ArtistProfile(User user, String bio) {
        this.user = user;
        this.bio = bio;
    }
}
