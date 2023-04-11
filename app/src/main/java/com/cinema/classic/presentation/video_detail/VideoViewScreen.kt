package com.cinema.classic.presentation.video_detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cinema.classic.presentation.video_detail.components.MovieDetail
import com.cinema.classic.presentation.video_detail.components.TabLayout
import com.cinema.classic.presentation.video_detail.components.YoutubeScreen


@Composable
fun VideoViewScreen(navController: NavController, viewModel: VideoViewModel = hiltViewModel()) {
    val state = viewModel.data.value
    state.movie?.let {
        Column {
            Box(
                modifier = Modifier
                    .then(
                        if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE)
                            Modifier.fillMaxSize()
                        else Modifier.height(IntrinsicSize.Min)
                    )
            ) {
                YoutubeScreen(videoId = viewModel.videoId)
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
                MovieDetail(it)
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                TabLayout(viewModel = viewModel)
            }
        }
    }
}

