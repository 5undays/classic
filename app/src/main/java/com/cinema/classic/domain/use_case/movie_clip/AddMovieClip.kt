package com.cinema.classic.domain.use_case.movie_clip

import com.cinema.classic.domain.model.MovieClip
import com.cinema.classic.domain.repository.MovieClipRepository

class AddMovieClip(private val movieClipRepository: MovieClipRepository) {

    suspend operator fun invoke(movieClip: MovieClip) {
        movieClipRepository.insertMovieClip(movieClip)
    }
}