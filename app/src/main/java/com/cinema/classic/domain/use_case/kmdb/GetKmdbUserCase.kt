package com.cinema.classic.domain.use_case.kmdb

import com.cinema.classic.common.Resource
import com.cinema.classic.data.remote.dto.toItem
import com.cinema.classic.domain.model.Kmdb
import com.cinema.classic.domain.model.Movie
import com.cinema.classic.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetKmdbUserCase constructor(private val repository: MovieRepository) {
    operator fun invoke(title: String): Flow<Resource<Kmdb>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.getMovieByKmdb(title).toItem()
            emit(Resource.Success(result))
        } catch (e: HttpException) {
            emit(Resource.Error(""))
        } catch (e: IOException) {
            emit(Resource.Error(""))
        }
    }
}