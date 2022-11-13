package com.cinema.classic.data.movie.impl

import com.cinema.classic.Retrofit
import com.cinema.classic.data.movie.MovieRepository
import com.cinema.classic.model.NaverResult
import com.cinema.classic.model.Youtube
import retrofit2.Call

class MovieService : MovieRepository {
    override fun get(url: String, post: String?): Call<NaverResult> {
        return Retrofit.api.get(url, post);
    }

    override fun get(
        url: String,
        part: String,
        channelId: String,
        order: String,
        key: String
    ): Call<Youtube> {
        return Retrofit.api.get(url);
    }
}