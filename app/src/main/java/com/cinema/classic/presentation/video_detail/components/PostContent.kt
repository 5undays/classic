package com.cinema.classic.presentation.video_detail.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.hilt.navigation.compose.hiltViewModel
import com.cinema.classic.presentation.ui.theme.ClassicTheme2
import com.cinema.classic.presentation.video_detail.VideoViewModel
import java.util.*


@Composable
fun movieData(viewModel: VideoViewModel = hiltViewModel()) {
    val data1 = viewModel.data.value.movie

    ClassicTheme2 {
        data1?.let {
            Column {
                Column(
                    modifier = Modifier
                        .then(
                            if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE)
                                Modifier.fillMaxSize()
                            else Modifier.height(IntrinsicSize.Min)
                        )
                ) {
                    YoutubePlayerView(
                        data1.title,
                        viewModel.videoId.toString(),
                        data1.pubDate,
                        viewModel
                    )
                }
                Column(
                    modifier = Modifier
                        .height(IntrinsicSize.Min)
                        .fillMaxWidth()
                ) {
                    detail(data1!!)
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {
                    tabLayout(viewModel = viewModel)
                }
            }
        }
    }
}


val pages = listOf("PLOT", "BOOKMARK")


