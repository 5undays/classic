package com.cinema.classic.domain.use_case.get_youtube

import com.cinema.classic.common.Resource
import com.cinema.classic.data.remote.dto.toItem
import com.cinema.classic.domain.model.Item
import com.cinema.classic.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetYoutubeListUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(): Flow<Resource<List<Item>>> = flow {
        try {
            emit(Resource.Loading())
            val list = repository.getYoutubeVideoList().items.map { it.toItem() }
            emit(Resource.Success(list))
        } catch (e: HttpException) {
            emit(Resource.Error("An Expected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. check your intract internet"))
        }
    }
}