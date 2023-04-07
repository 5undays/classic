package com.cinema.classic.data.repository

import com.cinema.classic.data.local.MovieClipApi
import com.cinema.classic.data.local.dto.MovieClip
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieClipRepository @Inject constructor(
    private val movieClipApi: MovieClipApi
) {
    fun get(videoId: String) = movieClipApi.get(videoId)

    suspend fun delete(movieClip: MovieClip) {
        movieClipApi.delete(movieClip)
    }

    suspend fun insert(movieClip: MovieClip) {
        movieClipApi.insert(movieClip)
    }

    fun getLastVideo() : Flow<MovieClip> = movieClipApi.getLastVideo()

    companion object {
        @Volatile
        private var instance: MovieClipRepository? = null

        fun getInstance(movieClipApi: MovieClipApi) =
            instance ?: synchronized(this) {
                instance ?: MovieClipRepository(movieClipApi).also { instance = it }
            }
    }
}