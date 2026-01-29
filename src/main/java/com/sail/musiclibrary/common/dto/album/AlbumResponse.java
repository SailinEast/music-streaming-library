package com.sail.musiclibrary.common.dto.album;

import java.util.List;

public record AlbumResponse(
    Long albumId,
    String title,
    Long artistId,
    List<String> songTitles
) {}