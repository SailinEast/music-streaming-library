package com.sail.musiclibrary.common.dto.song;

public record SongResponse(
    Long songId,
    String title,
    Long albumId,
    Long artistUserId
) {}
