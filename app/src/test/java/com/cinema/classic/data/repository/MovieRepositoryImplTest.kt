package com.cinema.classic.data.repository

import androidx.paging.PagingData
import com.cinema.classic.data.remote.dto.KmdbDto
import com.cinema.classic.data.remote.dto.NaverDto
import com.cinema.classic.domain.model.Movie
import com.cinema.classic.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImplTest : MovieRepository {

//    private val youtubeList = PagingData<Movie>();
//    private val naverList = mutableListOf<NaverDto>();

    override suspend fun getYoutubeVideoList(): Flow<PagingData<Movie>> {
//        return flow { emit(youtubeList) }
        TODO("Not yet implemented")
    }

    override suspend fun getMovieByNaver(title: String, year: Int): NaverDto {
//        return naverList[0].items[0]
        TODO("Not yet implemented")
    }

    override suspend fun getMovieByKmdb(title: String): KmdbDto {
        TODO("Not yet implemented")
    }


}