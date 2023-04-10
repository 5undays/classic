package com.cinema.classic.presentation.video_detail

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cinema.classic.presentation.video_detail.components.MovieDetail
import com.cinema.classic.presentation.video_detail.components.TabLayout
import com.cinema.classic.presentation.video_detail.components.YoutubePlayerView


@Composable
fun VideoViewScreen(navController: NavController, viewModel: VideoViewModel = hiltViewModel()) {
    val movie = viewModel.data.value.movie

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
//                YoutubeScreen(videoId = viewModel.videoId.toString())
                YoutubePlayerView(
                    movie.title,
                    viewModel.videoId.toString(),
                    movie.year,
                    viewModel
                )
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
                TabLayout(viewModel = viewModel)
            }
        }
    }

}

@Preview
@Composable
fun ArtistCard() {
    Column {
        Text("Alfred Sisley")
        Text("3 minutes ago")
    }
}

@Preview
@Composable
fun PreviewGreeting() {
    //Column {

    Box(
        modifier = Modifier
            //.fillMaxHeight()
            .background(color = Color.Blue)
            .fillMaxWidth()
    ) {
        Text("test2")
    }
    Box(
        modifier = Modifier
            .background(color = Color.LightGray)
            //.height(IntrinsicSize.Min)
            .fillMaxWidth()
    ) {
        Text("test33")
    }
    //}

}





