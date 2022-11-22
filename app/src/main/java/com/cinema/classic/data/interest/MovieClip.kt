package com.cinema.classic.data.interest

import androidx.room.Entity

@Entity(tableName = "movie_clip", primaryKeys = ["video_id","time"])
data class MovieClip(
    val video_id: String,
    val time:String,
    val reg_date: String
)