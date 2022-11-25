package com.cinema.classic.data.interest

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieClipDao {
    @Query("SELECT * FROM movie_clip")
    fun getAll(): List<MovieClip>

    @Insert
    fun insert(movieClip: MovieClip)

    @Delete
    fun delete(movieClip: MovieClip)
}