package com.cinema.classic.domain.repository

import com.cinema.classic.data.remote.dto.*

interface MovieRepository {
    suspend fun getYoutubeVideoList(): Youtube
    suspend fun getMovieByNaver(title: String, year: Int): NaverDto
    suspend fun getMovieByKmdb(title: String): KmdbDto
}