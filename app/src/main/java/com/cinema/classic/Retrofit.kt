package com.cinema.classic

import com.cinema.classic.api.MovieService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    private val naver_retrofit = Retrofit.Builder()
        //.baseUrl("https://openapi.naver.com/v1/search/")
        .baseUrl("https://www.googleapis.com/youtube/v3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val _api = naver_retrofit.create(MovieService::class.java)
    val api
        get() = _api
}