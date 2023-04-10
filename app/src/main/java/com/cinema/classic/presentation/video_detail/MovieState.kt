package com.cinema.classic.presentation.video_detail

import com.cinema.classic.domain.model.MovieClip
import com.cinema.classic.domain.model.NaverMovie
import com.cinema.classic.domain.model.Plot

data class MovieState(
    val isLoading: Boolean = false,
    val movie: NaverMovie? = null,
    val plot: Plot? = null,
    val movieClips:List<MovieClip> = emptyList(),
    val error: String = ""
)