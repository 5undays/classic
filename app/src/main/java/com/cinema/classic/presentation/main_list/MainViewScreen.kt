package com.cinema.classic.presentation.main_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cinema.classic.R
import com.cinema.classic.presentation.main_list.components.AppBar
import com.cinema.classic.presentation.main_list.components.Header
import com.cinema.classic.presentation.main_list.components.LastMovie
import com.cinema.classic.presentation.main_list.components.MovieItem
import com.cinema.classic.presentation.util.Screen

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel = hiltViewModel()) {
    val data = viewModel.data.value

    Scaffold(
        topBar = { AppBar() }
    ) { innerPadding ->
        data.movie?.let {
            Column(modifier = Modifier.fillMaxWidth()) {
                Header(text = stringResource(R.string.popular))
                if (data.lastClip != null) {
                    LastMovie(data.movie, data.lastClip)
                }
            }
        }
        LazyColumn(contentPadding = innerPadding) {
            item {
                Header(stringResource(R.string.korean_film))
            }
            items(data.youtubeList) { movie ->
                MovieItem(
                    movie = movie,
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.VideoViewScreen.route
                                + "?videoId=${movie.videoId}&title=${movie.title}&year=${movie.year}") }
                )
            }
        }
    }

}


