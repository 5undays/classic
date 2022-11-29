package com.cinema.classic.data.interest

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieClipDao {
    @Query("SELECT * FROM movie_clip WHERE video_id = :videoId")
    suspend fun get(videoId : String): List<MovieClip>

    @Insert
    fun insert(movieClip: MovieClip)

    @Delete
    fun delete(movieClip: MovieClip)
}