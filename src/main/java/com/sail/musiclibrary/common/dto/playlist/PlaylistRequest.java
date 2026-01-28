package com.sail.musiclibrary.common.dto.playlist;

public record PlaylistRequest (
    String name,
    Long ownerId
) {}