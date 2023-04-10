package com.cinema.classic.domain.use_case.kmdb

import com.cinema.classic.common.Resource
import com.cinema.classic.data.remote.dto.toItem
import com.cinema.classic.domain.model.Plot
import com.cinema.classic.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetKmdbUserCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(title: String): Flow<Resource<Plot>> = flow {
        try {
            emit(Resource.Loading())
            val result =
                repository.getMovieByKmdb(title).Data[0].Result[0].plots.plotDto[0].toItem()
            emit(Resource.Success(result))
        } catch (e: HttpException) {
            emit(Resource.Error(""))
        } catch (e: IOException) {
            emit(Resource.Error(""))
        }
    }
}