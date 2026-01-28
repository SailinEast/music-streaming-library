package com.sail.musiclibrary.media.album.song;

import com.sail.musiclibrary.common.dto.song.SongRequest;
import com.sail.musiclibrary.common.dto.song.SongResponse;
import com.sail.musiclibrary.common.dto.song.SongUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/songs")
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;

    // Get song
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

    // Create song
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

    // Rename song
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

    // Delete song
    @DeleteMapping("/{songId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSong(@PathVariable Long songId, @RequestParam Long requesterId) {
        songService.deleteSong(songId, requesterId);
    }
}
