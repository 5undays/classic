package com.cinema.classic.domain.repository

import androidx.paging.PagingData
import com.cinema.classic.data.remote.dto.KmdbDto
import com.cinema.classic.data.remote.dto.NaverDto
import com.cinema.classic.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getYoutubeVideoList(): Flow<PagingData<Movie>>
    suspend fun getMovieByNaver(title: String, year: Int): NaverDto
    suspend fun getMovieByKmdb(title: String): KmdbDto
}