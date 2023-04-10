package com.cinema.classic.domain.use_case

import com.cinema.classic.domain.use_case.kmdb.GetKmdbUserCase
import com.cinema.classic.domain.use_case.movie_clip.AddMovieClip
import com.cinema.classic.domain.use_case.movie_clip.DeleteMovieClip
import com.cinema.classic.domain.use_case.movie_clip.GetMovieClips
import com.cinema.classic.domain.use_case.naver.GetNaverUseCase

data class VideoViewUseCase(
    val getKmdbUserCase: GetKmdbUserCase,
    val getNaverUseCase: GetNaverUseCase,
    val addMovieClip: AddMovieClip,
    val deleteMovieClip: DeleteMovieClip,
    val getMovieClips: GetMovieClips
)
