package com.sail.musiclibrary.common.dto.playlist;

import java.util.List;

public record PlaylistResponse (
    String name,
    Long playlistId,
    Long ownerId,
    List<String> songsTitles
) {}
