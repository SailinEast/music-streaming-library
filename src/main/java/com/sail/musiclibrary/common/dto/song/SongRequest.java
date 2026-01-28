package com.sail.musiclibrary.common.dto.song;

public record SongRequest(
    String title,
    int duration,
    Long albumId,
    Long artistUserId
) {}