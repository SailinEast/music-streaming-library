package com.sail.musiclibrary.media;

import com.sail.musiclibrary.artist.ArtistProfile;
import com.sail.musiclibrary.media.album.Album;
import com.sail.musiclibrary.media.album.AlbumRepository;
import com.sail.musiclibrary.media.album.song.Song;
import com.sail.musiclibrary.media.album.song.SongRepository;
import com.sail.musiclibrary.user.User;
import com.sail.musiclibrary.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaService {
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;
    private final UserService userService;




    public Song findSongById(Long id) {
        return songRepository.findById(id).orElseThrow();
    }

    public Album findAlbumById(Long id) {
        return albumRepository.findById(id).orElseThrow();
    }
}
