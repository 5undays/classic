package com.cinema.classic.presentation.main_list

import com.cinema.classic.domain.model.Item

data class MainViewState(
    val isLoading: Boolean = false,
    val youtubeList: List<Item> = emptyList(),
    val error: String = ""
)