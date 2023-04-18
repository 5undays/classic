package com.cinema.classic.data.repository

import com.cinema.classic.domain.model.MovieClip
import com.cinema.classic.domain.repository.MovieClipRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


internal class MovieClipRepositoryImplTest : MovieClipRepository {

    private val movieClips = mutableListOf<MovieClip>();
    override fun getMovieClips(videoId: String): Flow<List<MovieClip>> {
        return flow { emit(movieClips) }
    }

    override suspend fun insertMovieClip(movieClip: MovieClip) {
        movieClips.add(movieClip)
    }

    override suspend fun deleteMovieClip(movieClip: MovieClip) {
        movieClips.remove(movieClip)
    }

    override suspend fun getLastVideo(): Flow<MovieClip> {
        return flow { emit(movieClips.get(movieClips.size - 1)) }
    }

}