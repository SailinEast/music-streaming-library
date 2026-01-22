package com.sail.musiclibrary.common.dto;

public record SongResponse(
        Long id,
        String title,
        String albumName,
        String artistName
) {}
