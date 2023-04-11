package com.cinema.classic.presentation.video_detail

import com.cinema.classic.domain.model.Kmdb
import com.cinema.classic.domain.model.MovieClip

data class MovieState(
    val isLoading: Boolean = false,
    val movie: Kmdb? = null,
    val movieClips: List<MovieClip> = emptyList(),
    val error: String = "",
    val orientation: Int = 0,
    var currentTime: Float = 0f
)