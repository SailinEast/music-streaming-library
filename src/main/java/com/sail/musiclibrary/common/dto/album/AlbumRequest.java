package com.sail.musiclibrary.common.dto.album;

public record AlbumRequest(
    String title,
    Long artistId
) {}