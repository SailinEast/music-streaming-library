package com.sail.musiclibrary.playlist;

import com.sail.musiclibrary.common.dto.playlist.PlaylistRequest;
import com.sail.musiclibrary.common.dto.playlist.PlaylistResponse;
import com.sail.musiclibrary.media.album.song.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("api/playlists")
@RequiredArgsConstructor
public class PlaylistController {
    public final PlaylistService playlistService;

    @GetMapping("/{playlistId}")
    public PlaylistResponse getPlaylist(@PathVariable Long playlistId) {
        Playlist playlist = playlistService.findById(playlistId);

        return new PlaylistResponse(
            playlist.getName(),
            playlist.getId(),
            playlist.getOwner().getId(),
            playlist.getSongs().stream().map(Song::getTitle).toList()
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlaylistResponse create(@RequestBody PlaylistRequest request) {
        Playlist playlist = playlistService.createPlaylist(
            request.name(),
            request.ownerId()
        );

        return new PlaylistResponse(
            playlist.getName(),
            playlist.getId(),
            playlist.getOwner().getId(),
            playlist.getSongs().stream().map(Song::getTitle).toList()
        );
    }

    @PostMapping("/{playlistId}/songs/{songId}")
    public void addSong(@PathVariable Long playlistId, @PathVariable Long songId, @RequestParam Long requesterId) {
        playlistService.addSongToPlaylist(playlistId, songId, requesterId);
    }

    @PutMapping("/{playlistId}")
    public PlaylistResponse updatePlaylist(
        @PathVariable Long playlistId,
        @RequestBody PlaylistRequest request,
        @RequestParam Long requesterId
    ) {
        Playlist updatedPlaylist = playlistService.renamePlaylist(playlistId, request.name(), requesterId);

        return new PlaylistResponse(
            updatedPlaylist.getName(),
            updatedPlaylist.getId(),
            updatedPlaylist.getOwner().getId(),
            Collections.emptyList()
        );
    }

    @DeleteMapping("/{playlistId}/songs/{songId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeSong(@PathVariable Long playlistId, @PathVariable Long songId, @RequestParam Long requesterId) {
        playlistService.removeSongFromPlaylist(playlistId, songId, requesterId);
    }
}
