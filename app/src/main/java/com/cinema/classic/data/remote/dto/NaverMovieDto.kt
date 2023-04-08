package com.cinema.classic.data.remote.dto

import com.cinema.classic.domain.model.NaverMovie
import com.google.gson.annotations.SerializedName

data class NaverMovieDto(
    @field:SerializedName("title") var title: String,
    @field:SerializedName("link") val link: String,
    @field:SerializedName("image") val image: String,
    @field:SerializedName("subtitle") val subtitle: String,
    @field:SerializedName("pubDate") val pubDate: String,
    @field:SerializedName("director") val director: String,
    @field:SerializedName("actor") var actor: String,
    @field:SerializedName("userRating") val userRating: String
)

fun NaverMovieDto.toItem(): NaverMovie {
    return NaverMovie(
        title = title,
        link = link,
        image = image,
        subtitle = subtitle,
        pubDate = pubDate,
        director = director,
        actor = actor,
        userRating = userRating
    )
}