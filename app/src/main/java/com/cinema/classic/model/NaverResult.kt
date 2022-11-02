package com.cinema.classic.model

import retrofit2.Call

data class NaverResult(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<NaverMovie>
)

data class NaverMovie(
    val title:String,
    val link:String,
    val image:String,
    val subtitle:String,
    val pubDate:String,
    val director:String,
    val actor:String,
    val userRating:String
)
