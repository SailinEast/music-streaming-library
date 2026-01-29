package com.sail.musiclibrary.media.album;

import com.sail.musiclibrary.common.dto.album.AlbumRequest;
import com.sail.musiclibrary.common.dto.album.AlbumResponse;
import com.sail.musiclibrary.media.album.song.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/albums")
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    // Get album
    @GetMapping("/{albumId}")
    public AlbumResponse getAlbum(@PathVariable Long albumId) {
        Album album = albumService.findById(albumId);

        return new AlbumResponse(
            album.getId(),
            album.getTitle(),
            album.getArtist().getUser().getId(),
            album.getSongs().stream().map(Song::getTitle).toList()
        );
    }

    // Create album
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumResponse uploadAlbum(@RequestBody AlbumRequest request, @RequestParam Long requesterId) {
        return albumService.releaseAlbum(
            request.artistId(),
            request.title(),
            requesterId
        );
    }

    // Rename album
    @PutMapping("/{albumId}")
    public AlbumResponse updateAlbum(@PathVariable Long albumId, @RequestParam Long requesterId, @RequestBody AlbumRequest request) {
        return albumService.renameAlbum(albumId, request.title(), requesterId);
    }

    // Delete album
    @DeleteMapping("/{albumId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable Long albumId, @RequestParam Long requesterId) {
        albumService.deleteAlbum(albumId, requesterId);
    }
}