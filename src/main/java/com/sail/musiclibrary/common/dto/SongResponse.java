package com.sail.musiclibrary.common.dto;

public record SongResponse(
    Long songId,
    String title,
    Long albumId,
    Long artistUserId
) {}
