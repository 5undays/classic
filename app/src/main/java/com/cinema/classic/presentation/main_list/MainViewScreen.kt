package com.cinema.classic.presentation.main_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.cinema.classic.R
import com.cinema.classic.presentation.main_list.components.AppBar
import com.cinema.classic.presentation.main_list.components.Header
import com.cinema.classic.presentation.main_list.components.LastMovie
import com.cinema.classic.presentation.main_list.components.PostItem
import com.cinema.classic.presentation.ui.theme.ClassicTheme
import androidx.compose.foundation.lazy.items

@Composable
fun Home(viewModel: MainViewModel = hiltViewModel()) {
    val list = viewModel.list.value
    val last = viewModel.lastClip.value
    val data2 = viewModel.lastMovieData.value

    ClassicTheme {
        Scaffold(
            topBar = { AppBar() }
        ) { innerPadding ->
            LazyColumn(contentPadding = innerPadding) {
                if (last != null) {
                    item {
                        Header(stringResource(R.string.popular))
                    }
                    item {
                        data2.movie?.let { LastMovie(it, last) }
                    }
                }
                item {
                    Header(stringResource(R.string.korean_film))
                }
                items(list.youtubeList) { post ->
                    PostItem(post = post)
                }
            }
        }
    }
}


