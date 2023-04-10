package com.cinema.classic.data.repository

import com.cinema.classic.common.Constants
import com.cinema.classic.data.remote.MovieApi
import com.cinema.classic.data.remote.dto.KmdbDto
import com.cinema.classic.data.remote.dto.NaverDto
import com.cinema.classic.data.remote.dto.Youtube
import com.cinema.classic.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRepository {
    override suspend fun getYoutubeVideoList(): Youtube {
        return movieApi.getYoutubeVideoList(Constants.BASE_URL)
    }

    override suspend fun getMovieByNaver(title: String, year: Int): NaverDto {
        return movieApi.getMovieByNaver(Constants.NAVER_URL, title, year, year)
    }

    override suspend fun getMovieByKmdb(title: String): KmdbDto {
        return movieApi.getMovieByKmdb(Constants.KMDB_URL, title)
    }
}