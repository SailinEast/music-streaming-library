package com.sail.musiclibrary.common.dto;

public record SongRequest(
    String title,
    int duration,
    Long albumId,
    Long artistUserId
) {}