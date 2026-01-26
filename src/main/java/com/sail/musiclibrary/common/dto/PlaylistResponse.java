package com.sail.musiclibrary.common.dto;

import java.util.List;

public record PlaylistResponse (
    String name,
    Long playlistId,
    Long ownerId,
    List<String> songsTitles
) {}
