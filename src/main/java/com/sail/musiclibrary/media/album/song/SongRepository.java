package com.sail.musiclibrary.media.album.song;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    @Modifying
    @Query(value = "DELETE FROM playlist_song WHERE song_id = :songId", nativeQuery = true)
    void removeSongFromAllPlaylists(Long songId);
}
