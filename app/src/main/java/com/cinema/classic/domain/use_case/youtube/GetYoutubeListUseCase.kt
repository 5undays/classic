package com.cinema.classic.domain.use_case.youtube

import com.cinema.classic.common.Constants
import com.cinema.classic.data.remote.dto.Youtube
import com.cinema.classic.domain.repository.MovieRepository
import javax.inject.Inject

class GetYoutubeListUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(pageToken: String?): Youtube {
        return repository.getYoutubeVideoList(url = Constants.BASE_URL, pageToken = pageToken)
    }
}