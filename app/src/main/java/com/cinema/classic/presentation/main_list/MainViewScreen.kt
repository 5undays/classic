package com.cinema.classic.presentation.main_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.cinema.classic.domain.model.Movie
import com.cinema.classic.presentation.main_list.components.AppBar
import com.cinema.classic.presentation.main_list.components.MovieItem
import com.cinema.classic.presentation.util.Screen

@Composable
fun MainScreen(navController: NavController
               , mainVideos: LazyPagingItems<Movie>) {

    Scaffold(
        topBar = { AppBar() }
    ) { innerPadding ->
//        lastVideo?.let {
//            Column(modifier = Modifier.fillMaxWidth()) {
//                Header(text = stringResource(R.string.popular))
//                LastMovie(lastVideo.movie, lastVideo.invoke())
//            }
//        }
        LazyColumn(contentPadding = innerPadding) {
            items(mainVideos) { movie ->
                if (movie != null) {
                    MovieItem(
                        movie = movie,
                        modifier = Modifier.clickable {
                            navController.navigate(
                                Screen.VideoViewScreen.route
                                        + "?videoId=${movie.videoId}&title=${movie.title}&year=${movie.year}"
                            )
                        }
                    )
                }
            }
        }
    }

}


