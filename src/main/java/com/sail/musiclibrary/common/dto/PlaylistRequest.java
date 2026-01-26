package com.sail.musiclibrary.common.dto;

public record PlaylistRequest (
    String name,
    Long ownerId
) {}