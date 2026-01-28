package com.sail.musiclibrary.media.album.song;

import com.sail.musiclibrary.common.dto.song.SongRequest;
import com.sail.musiclibrary.common.dto.song.SongResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/songs")
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;

    @GetMapping


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SongResponse uploadSong(@RequestBody SongRequest request) {
        Song song = songService.uploadSong(
            request.albumId(),
            request.title(),
            request.duration(),
            request.artistUserId()
        );

        return new SongResponse(
            song.getId(),
            song.getTitle(),
            song.getAlbum().getTitle(),
            song.getAlbum().getArtist().getUser().getHandle()
        );
    }
}
