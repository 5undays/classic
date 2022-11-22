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

//    @Query("SELECT * FROM user WHERE uid IN (:video_id)")
//    fun loadAllByIds(userIds: IntArray): List<User>
//
//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movieClip: List<MovieClip>)

    @Delete
    fun delete(movieClip: MovieClip)
}