package com.sail.musiclibrary.access;

import com.sail.musiclibrary.user.User;
import com.sail.musiclibrary.user.UserLookupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ArtistAccessValidator {
    private final UserLookupService userLookupService;

    public User validate(Long userId, Long requesterId) {
        User user = userLookupService.findById(userId);
        if (!user.isArtist()) {
            throw new IllegalStateException("User '" + user.getHandle() + "' is not an artist!");
        }
        if (!Objects.equals(userId, requesterId)) {
            throw new SecurityException("Access Denied");
        }
        return user;
    }
}
