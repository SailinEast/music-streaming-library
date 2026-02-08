package com.sail.musiclibrary.access;

import com.sail.musiclibrary.artist.ArtistProfile;
import com.sail.musiclibrary.media.album.Album;
import com.sail.musiclibrary.media.album.AlbumService;
import com.sail.musiclibrary.user.UserLookupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlbumOwnershipPolicyImpl implements AlbumOwnershipPolicy {
    private final AlbumService albumService;
    private final UserLookupService userLookupService;

    @Override
    public Album validateOwner(Long albumId, Long requesterId) {
        Album album = albumService.findById(albumId);
        ArtistProfile artist = userLookupService.findById(requesterId).getArtistProfile();
        if (!album.getArtist().equals(artist)) {
            throw new SecurityException("Access Denied");
        }
        return album;
    }
}
