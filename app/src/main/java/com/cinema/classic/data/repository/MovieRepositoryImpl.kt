package com.cinema.classic.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.cinema.classic.common.Constants
import com.cinema.classic.data.remote.MovieApi
import com.cinema.classic.data.remote.dto.KmdbDto
import com.cinema.classic.data.remote.dto.NaverDto
import com.cinema.classic.data.remote.dto.Youtube
import com.cinema.classic.domain.model.Movie
import com.cinema.classic.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRepository {
//    override suspend fun getYoutubeVideoList(): Youtube {
//        return movieApi.getYoutubeVideoList(Constants.BASE_URL)
//    }

    override suspend fun getYoutubeVideoList(): Flow<PagingData<Movie>> {
        return Pager(PagingConfig(pageSize = 10)) {
            YotubePagingSource(movieApi)
        }.flow
    }

    override suspend fun getMovieByNaver(title: String, year: Int): NaverDto {
        return movieApi.getMovieByNaver(Constants.NAVER_URL, title, year, year)
    }

    override suspend fun getMovieByKmdb(title: String): KmdbDto {
        return movieApi.getMovieByKmdb(Constants.KMDB_URL, title)
    }
}