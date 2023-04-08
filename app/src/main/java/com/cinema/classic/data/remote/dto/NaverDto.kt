package com.cinema.classic.data.remote.dto

import com.cinema.classic.domain.model.Naver
import com.google.gson.annotations.SerializedName

data class NaverDto(
    @field:SerializedName("lastBuildDate") val lastBuildDate: String,
    @field:SerializedName("total") val total: Int,
    @field:SerializedName("start") val start: Int,
    @field:SerializedName("display") val display: Int,
    @field:SerializedName("items") val items: List<NaverMovieDto>
)

fun NaverDto.toItem(): Naver {
    return Naver(
        lastBuildDate = lastBuildDate,
        total = total,
        start = start,
        display = display,
        items = items
    )
}

