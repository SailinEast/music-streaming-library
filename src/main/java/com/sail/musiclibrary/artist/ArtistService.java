package com.sail.musiclibrary.artist;

import com.sail.musiclibrary.user.User;
import com.sail.musiclibrary.user.UserLookupService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final UserLookupService userLookupService;
    private final ArtistRepository artistRepository;

    @Transactional
    public ArtistProfile promote(Long userId, String bio) {
        User user = userLookupService.findById(userId);
        if (user.isArtist()) return user.getArtistProfile();

        ArtistProfile artist = new ArtistProfile(user, bio);
        user.setArtistProfile(artist);
        return artistRepository.save(artist);
    }
}
