package com.cinema.classic.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cinema.classic.data.remote.dto.ItemDto
import com.cinema.classic.domain.model.Movie

@Entity
data class YoutubeEntity (
    @PrimaryKey
    //val id : Int,
    val videoId: String,
    val title: String,
    val year: Int,
    val title_english: String,
    val thumbnail: String,
    val description : String,
)

fun ItemDto.toItem(): YoutubeEntity {
    val title = snippet.title.split("/")[0].split("(")
    var year: Int = 1900
    var titleEnglish = "";
//    if (title.size > 2) {
//        titleEnglish = title[1];
//        year = title[1].replace(")", "").trim().replace("[^0-9]".toRegex(), "").toInt()
//    }
    return YoutubeEntity(
        title = title[0],
        description = snippet.description,
        thumbnail = snippet.thumbnails.high.url,
        title_english = titleEnglish,
        videoId = id.videoId,
        year = year
    )
}