package com.cinema.classic.presentation.main_list

import com.cinema.classic.domain.model.Item
import com.cinema.classic.domain.model.MovieClip
import com.cinema.classic.domain.model.NaverMovie

data class MainViewState(
    val isLoading: Boolean = false,
    val youtubeList: List<Item> = emptyList(),
    val movie: NaverMovie? = null,
    val lastClip: MovieClip? = null,
    val error: String = ""
)