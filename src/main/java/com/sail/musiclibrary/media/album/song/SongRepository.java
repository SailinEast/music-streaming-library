package com.sail.musiclibrary.media.album.song;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    @Modifying
    @Query(value = "DELETE FROM playlist_song WHERE song_id = :songId", nativeQuery = true)
    void removeSongFromAllPlaylists(Long songId);
    
    @Modifying
    @Query(value = "DELETE FROM playlist_song WHERE song_id IN (SELECT id FROM songs WHERE album_id = :albumId)", nativeQuery = true)
    void removeSongsFromPlaylistsByAlbums(Long albumId);

    @Modifying
    @Query(value = "DELETE FROM Song s WHERE s.album.id = :albumId")
    void deleteAllByAlbumId(Long albumId);

    List<Song> findAllByAlbumId(Long albumId);
}
