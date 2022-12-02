package com.cinema.classic.data.interest

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieClipDao {
    @Query("SELECT * FROM movie_clip WHERE video_id = :videoId ORDER BY reg_date DESC")
    suspend fun get(videoId : String): List<MovieClip>

    @Insert
    fun insert(movieClip: MovieClip)

    @Delete
    suspend fun delete(movieClip: MovieClip)

//    @Query("SELECT 1 FROM movie_clip ORDER BY reg_date DESC")
//    suspend fun getLastVideo() : MovieClip
}