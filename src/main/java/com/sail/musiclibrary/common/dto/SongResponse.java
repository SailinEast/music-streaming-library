package com.sail.musiclibrary.common.dto;

public record SongResponse(
    Long id,
    String title,
    String albumTitle,
    String artistHandle
) {}
