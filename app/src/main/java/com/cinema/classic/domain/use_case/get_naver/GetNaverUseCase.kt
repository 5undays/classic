package com.cinema.classic.domain.use_case.get_naver

import com.cinema.classic.common.Resource
import com.cinema.classic.data.remote.dto.toItem
import com.cinema.classic.domain.model.NaverMovie
import com.cinema.classic.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNaverUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(title: String, year: Int): Flow<Resource<NaverMovie>> = flow {
        try {
            emit(Resource.Loading())
            val movie = repository.getMovieByNaver(title, year).items[0].toItem();
            emit(Resource.Success(movie))
        } catch (e: HttpException) {
            emit(Resource.Error("An Expected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. check your intract internet"))
        }
    }
}