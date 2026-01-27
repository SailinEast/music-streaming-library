package com.sail.musiclibrary.media.album.song;

import com.sail.musiclibrary.common.dto.SongRequest;
import com.sail.musiclibrary.common.dto.SongResponse;
import com.sail.musiclibrary.common.dto.SongUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/songs")
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;

    @GetMapping("/{songId}")
    public SongResponse getSong(@PathVariable Long songId) {
        Song song = songService.findById(songId);

        return new SongResponse(
            song.getId(),
            song.getTitle(),
            song.getAlbum().getId(),
            song.getAlbum().getArtist().getUser().getId()
        );
    }


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
            song.getAlbum().getId(),
            song.getAlbum().getArtist().getUser().getId()
        );
    }

    @PutMapping("/{songId}")
    public SongResponse updateSong(
        @PathVariable Long songId,
        @RequestParam Long requesterId,
        @RequestBody SongUpdate request
    ) {
        Song updatedSong = songService.renameSong(songId, request.title(), requesterId);

        return new SongResponse(
            updatedSong.getId(),
            updatedSong.getTitle(),
            updatedSong.getAlbum().getId(),
            updatedSong.getAlbum().getArtist().getUser().getId()
        );
    }

    @DeleteMapping("/{songId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSong(@PathVariable Long songId, @RequestParam Long requesterId) {
        songService.deleteSong(songId, requesterId);
    }
}
