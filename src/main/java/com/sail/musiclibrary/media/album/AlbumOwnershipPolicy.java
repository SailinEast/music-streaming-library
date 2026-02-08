package com.sail.musiclibrary.media.album;

public interface AlbumOwnershipPolicy {
    Album validateOwner(Long albumId, Long requesterId);
}
