package com.cinema.classic.domain.model

data class NaverMovie(
    var title: String,
    val link: String,
    val image: String,
    val subtitle: String,
    val pubDate: String,
    val director: String,
    var actor: String,
    val userRating: String
)
