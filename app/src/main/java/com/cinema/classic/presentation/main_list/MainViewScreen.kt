package com.cinema.classic.presentation.main_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

@Composable
fun Home(viewModel: MainViewModel = hiltViewModel()) {
    val list = viewModel.data.value
    val last = viewModel.data.value.lastClip
    val data2 = viewModel.data.value.movie

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
                        data2?.let { LastMovie(it, last) }
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


