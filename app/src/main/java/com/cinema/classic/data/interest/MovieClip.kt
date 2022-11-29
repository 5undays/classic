package com.cinema.classic.data.interest

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.util.*

@Entity(tableName = "movie_clip", primaryKeys = ["video_id", "time"])
data class MovieClip(
    @ColumnInfo(name = "video_id") val video_id: String,
    @ColumnInfo(name = "time") val time: Float = 0f,
    @ColumnInfo(name = "reg_date") val reg_date: Calendar = Calendar.getInstance()
)