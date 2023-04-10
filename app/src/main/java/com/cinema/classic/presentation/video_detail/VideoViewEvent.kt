package com.cinema.classic.presentation.video_detail

import com.cinema.classic.domain.model.MovieClip

sealed class VideoViewEvent {
    data class AddMovieClip(val movieClip: MovieClip) : VideoViewEvent()
    data class DeleteMovieClip(val movieClip: MovieClip) : VideoViewEvent()
//    data class GetMovieClips(val videoId: String) : VideoViewEvent()
//    data class GetPlot(val title: String) : VideoViewEvent()
//    data class GetMovie(val title: String) : VideoViewEvent()
}
