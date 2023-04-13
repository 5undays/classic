package com.cinema.classic.data.remote.dto

import com.cinema.classic.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class ItemDto(
    @field:SerializedName("kind") val kind: String,
    @field:SerializedName("etag") val etag: String,
    @field:SerializedName("id") val id: Id,
    @field:SerializedName("snippet") val snippet: Snippet
)

fun ItemDto.toItem(): Movie {
    val title = snippet.title.split("/")[0].split("(")
    var year: Int = 1900
    var titleEnglish = "";
//    if (title.size > 2) {
//        titleEnglish = title[1];
//        year = title[1].replace(")", "").trim().replace("[^0-9]".toRegex(), "").toInt()
//    }
    return Movie(
        title = title[0],
        description = snippet.description,
        thumbnail = snippet.thumbnails.high.url,
        title_english = titleEnglish,
        videoId = id.videoId,
        year = year
    )
}