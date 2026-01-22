package com.sail.musiclibrary.media.album;

import com.sail.musiclibrary.common.BaseService;
import com.sail.musiclibrary.user.User;
import com.sail.musiclibrary.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlbumService extends BaseService<Album, Long> {
    private final AlbumRepository albumRepository;
    private final UserService userService;

    @Transactional
    public Album releaseAlbum(Long userId, String title) {
        User user = userService.findById(userId);
        if (!user.isArtist()) {
            throw new IllegalStateException("User '" + user.getHandle() + "' is not an artist");
        }

        Album album = new Album(title, user.getArtistProfile());
        return albumRepository.save(album);
    }

    @Override
    protected JpaRepository<Album, Long> getRepository() {
        return albumRepository;
    }
}
