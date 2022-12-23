package com.cinema.classic.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieClipRepository @Inject constructor(
    private val movieClipDao: MovieClipDao
) {
    fun get(videoId: String) = movieClipDao.get(videoId)

    suspend fun delete(movieClip: MovieClip) {
        movieClipDao.delete(movieClip)
    }

    suspend fun insert(movieClip: MovieClip) {
        movieClipDao.insert(movieClip)
    }

    fun getLastVideo() : Flow<MovieClip> = movieClipDao.getLastVideo()

    companion object {
        @Volatile
        private var instance: MovieClipRepository? = null

        fun getInstance(movieClipDao: MovieClipDao) =
            instance ?: synchronized(this) {
                instance ?: MovieClipRepository(movieClipDao).also { instance = it }
            }
    }
}