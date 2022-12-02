package com.cinema.classic.repository

import com.cinema.classic.data.interest.MovieClip
import com.cinema.classic.data.interest.MovieClipDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieClipRepository @Inject constructor(
    private val movieClipDao: MovieClipDao
) {
    suspend fun get(videoId: String) = movieClipDao.get(videoId)

    suspend fun delete(movieClip: MovieClip) {
        movieClipDao.delete(movieClip)
    }

    suspend fun insert(movieClip: MovieClip) {
        movieClipDao.insert(movieClip)
    }



    companion object {
        @Volatile
        private var instance: MovieClipRepository? = null

        fun getInstance(movieClipDao: MovieClipDao) =
            instance ?: synchronized(this) {
                instance ?: MovieClipRepository(movieClipDao).also { instance = it }
            }
    }
}