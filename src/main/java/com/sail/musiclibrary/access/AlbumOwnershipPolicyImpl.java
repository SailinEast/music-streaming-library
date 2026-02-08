package com.sail.musiclibrary.access;

import com.sail.musiclibrary.media.album.Album;
import com.sail.musiclibrary.media.album.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlbumOwnershipPolicyImpl implements AlbumOwnershipPolicy {
    private final AlbumRepository albumRepository;

    @Override
    public Album validateOwner(Long albumId, Long requesterId) {
        Album album = albumRepository.findById(albumId).orElseThrow();
        if (!album.getArtist().getUser().getId().equals(requesterId)) {
            throw new SecurityException("Access Denied");
        }
        return album;
    }
}
