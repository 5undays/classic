package com.cinema.classic.domain.use_case

import com.cinema.classic.domain.use_case.movie_clip.GetLastMovieClip
import com.cinema.classic.domain.use_case.naver.GetNaverUseCase
import com.cinema.classic.domain.use_case.youtube.GetYoutubeListUseCase

data class MainUseCase(
    val getLastMovieClip: GetLastMovieClip,
    val getYoutubeListUseCase: GetYoutubeListUseCase,
    val getNaverUseCase: GetNaverUseCase
)
