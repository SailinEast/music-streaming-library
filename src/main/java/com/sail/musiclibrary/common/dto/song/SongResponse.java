package com.sail.musiclibrary.common.dto.song;

public record SongResponse(
    Long id,
    String title,
    String albumTitle,
    String artistHandle
) {}
