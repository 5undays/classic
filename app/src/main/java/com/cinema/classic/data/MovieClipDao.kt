package com.cinema.classic.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieClipDao {
    @Query("SELECT * FROM movie_clip WHERE video_id = :videoId ORDER BY reg_date DESC")
    fun get(videoId : String): Flow<List<MovieClip>>

    @Insert
    fun insert(movieClip: MovieClip)

    @Delete
    suspend fun delete(movieClip: MovieClip)

    @Query("SELECT * FROM movie_clip ORDER BY reg_date DESC LIMIT 1")
    fun getLastVideo(): Flow<MovieClip>
}