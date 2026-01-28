package com.sail.musiclibrary.common.dto.user;

import java.util.List;

public record UserResponse(
    Long userId,
    String handle,
    boolean isArtist,
    List<String> userPlaylistNames
) {}