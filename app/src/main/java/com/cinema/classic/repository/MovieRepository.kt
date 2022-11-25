package com.cinema.classic.repository

import com.cinema.classic.api.MovieService
import com.cinema.classic.model.Item
import com.cinema.classic.model.Youtube
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

class MovieRepository @Inject constructor(
    private val service: MovieService
) {
    suspend fun load(): Response<Youtube> {
        return service.get("https://www.googleapis.com/youtube/v3/search")
    }
}