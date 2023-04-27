package com.cinema.classic.data.repository

import com.cinema.classic.data.local.MovieClipDao
import com.cinema.classic.domain.model.MovieClip
import com.cinema.classic.domain.repository.MovieClipRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieClipRepositoryImpl @Inject constructor(
    private val movieClipDao: MovieClipDao
) : MovieClipRepository {
    override fun getMovieClips(videoId: String): Flow<List<MovieClip>> {
        return movieClipDao.getMovieClips(videoId)
    }

    override suspend fun insertMovieClip(movieClip: MovieClip) {
        movieClipDao.insertMovieClip(movieClip)
    }

    override suspend fun deleteMovieClip(movieClip: MovieClip) {
        movieClipDao.deleteMovieClip(movieClip)
    }

    override  fun getLastVideo(): Flow<MovieClip> {
        return movieClipDao.getLastVideo()
    }
}