package com.cinema.classic.domain.model

import com.cinema.classic.data.remote.dto.NaverMovieDto

data class Naver(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<NaverMovieDto>
)