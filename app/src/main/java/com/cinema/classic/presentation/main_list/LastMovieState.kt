package com.cinema.classic.presentation.main_list

import com.cinema.classic.domain.model.NaverMovie

data class LastMovieState(
    val isLoading: Boolean = false,
    val movie: NaverMovie? = null,
    val error: String = ""
)