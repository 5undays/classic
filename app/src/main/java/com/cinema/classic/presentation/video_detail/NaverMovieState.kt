package com.cinema.classic.presentation.video_detail

import com.cinema.classic.domain.model.NaverMovie

data class NaverMovieState(
    val isLoading: Boolean = false,
    val movie: NaverMovie? = null,
    val error: String = ""
)