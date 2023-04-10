package com.cinema.classic.domain.model

data class Movie(
    val title: String,
    val year: Int,
    val title_english: String,
    val thumbnail: String,
    val videoId: String,
    val description : String,
)