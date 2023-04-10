package com.cinema.classic.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.util.*

@Entity(tableName = "movie_clip", primaryKeys = ["video_id", "time"])
data class MovieClip(
    @ColumnInfo(name = "video_id") val video_id: String,
    @ColumnInfo(name = "time") val time: Float = 0f,
    @ColumnInfo(name = "movie_name") val movie_name: String = "",
    @ColumnInfo(name = "movie_year") val movie_year: Int,
    @ColumnInfo(name = "reg_date") val reg_date: Calendar = Calendar.getInstance()
)

class InvalidMovieClipException(message: String) : Exception(message)