package com.sail.musiclibrary.artist;

import com.sail.musiclibrary.user.User;
import com.sail.musiclibrary.user.UserRepository;
import com.sail.musiclibrary.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final UserService userService;
    private final ArtistRepository artistRepository;

    @Transactional
    public ArtistProfile promote(Long userId, String bio) {
        User user = userService.findById(userId);
        if (user.isArtist()) return user.getArtistProfile();

        ArtistProfile artist = new ArtistProfile(user, bio);
        user.setArtistProfile(artist);
        return artistRepository.save(artist);
    }
}
