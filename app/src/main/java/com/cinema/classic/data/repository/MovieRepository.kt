package com.cinema.classic.data.repository

import com.cinema.classic.common.Constants
import com.cinema.classic.data.KmdbResult
import com.cinema.classic.data.NaverResult
import com.cinema.classic.data.Youtube
import com.cinema.classic.data.remote.MovieApi
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val service: MovieApi
) {
    suspend fun load(): Response<Youtube> {
        return service.get(Constants.BASE_URL)
    }

    suspend fun get(title: String, year: Int): Response<NaverResult> {
        return service.get2(Constants.NAVER_URL, title, year, year)
    }

    suspend fun get2(title: String): Response<KmdbResult> {
        return service.get3(Constants.KMDB_URL, title)
    }
}