package com.sail.musiclibrary.access;

import com.sail.musiclibrary.media.album.Album;

public interface AlbumOwnershipPolicy {
    Album validateOwner(Long albumId, Long requesterId);
}
