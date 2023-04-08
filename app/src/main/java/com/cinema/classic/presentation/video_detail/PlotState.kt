package com.cinema.classic.presentation.video_detail

import com.cinema.classic.domain.model.Plot

data class PlotState(
    val isLoading: Boolean = false,
    val movie: Plot? = null,
    val error: String = ""
)