package com.cinema.classic.domain.use_case.movie_clip

import com.cinema.classic.domain.model.MovieClip
import com.cinema.classic.domain.repository.MovieClipRepository

class DeleteMovieClip(private val repository: MovieClipRepository) {
    suspend operator fun invoke(movieClip: MovieClip) {
        repository.deleteMovieClip(movieClip)
    }
}