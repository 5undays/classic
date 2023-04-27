package com.cinema.classic.domain.use_case.movie_clip

import com.cinema.classic.domain.model.MovieClip
import com.cinema.classic.domain.repository.MovieClipRepository
import kotlinx.coroutines.flow.Flow

class GetLastMovieClip(private val repository: MovieClipRepository) {
     operator fun invoke(): Flow<MovieClip> {
        return repository.getLastVideo()
    }
}