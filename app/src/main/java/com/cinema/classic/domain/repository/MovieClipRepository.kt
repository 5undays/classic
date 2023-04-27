package com.cinema.classic.domain.repository

import com.cinema.classic.domain.model.MovieClip
import kotlinx.coroutines.flow.Flow

interface MovieClipRepository {

    fun getMovieClips(videoId: String): Flow<List<MovieClip>>

    suspend fun insertMovieClip(movieClip: MovieClip)

    suspend fun deleteMovieClip(movieClip: MovieClip)

     fun getLastVideo(): Flow<MovieClip>
}