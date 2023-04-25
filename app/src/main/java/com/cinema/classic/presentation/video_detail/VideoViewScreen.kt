package com.cinema.classic.presentation.video_detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavController
import com.cinema.classic.domain.model.Kmdb
import com.cinema.classic.domain.model.MovieClip
import com.cinema.classic.presentation.video_detail.components.MovieDetail
import com.cinema.classic.presentation.video_detail.components.TabLayout
import com.cinema.classic.presentation.video_detail.components.YoutubeScreen


@Composable
fun VideoViewScreen(
    navController: NavController,
    movie: Kmdb?,
    videoId: String,
    movieClips: List<MovieClip>,
    onEvent: (VideoViewEvent) -> Unit
) {
    movie?.let {
        Column {
            Box(
                modifier = Modifier
                    .then(
                        if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE)
                            Modifier.fillMaxSize()
                        else Modifier.height(IntrinsicSize.Min)
                    )
            ) {
                YoutubeScreen(videoId = videoId)
//                YoutubePlayerView(
//                    movie.title,
//                    viewModel.videoId.toString(),
//                    movie.year,
//                    viewModel,
//                )
            }
            Box(
                modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .fillMaxWidth()
            ) {
                MovieDetail(movie)
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                TabLayout(movie = movie, movieClips = movieClips, onEvent = onEvent)
            }
        }
    }
}

