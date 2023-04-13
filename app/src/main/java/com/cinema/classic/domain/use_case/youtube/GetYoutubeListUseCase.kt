package com.cinema.classic.domain.use_case.youtube

import androidx.paging.PagingData
import com.cinema.classic.domain.model.Movie
import com.cinema.classic.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetYoutubeListUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend operator fun invoke(): Flow<PagingData<Movie>> = repository.getYoutubeVideoList()
}