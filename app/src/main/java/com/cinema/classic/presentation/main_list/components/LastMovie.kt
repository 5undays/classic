package com.cinema.classic.presentation.main_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.cinema.classic.domain.model.MovieClip
import com.cinema.classic.presentation.util.Screen

@Composable
fun LastMovie(movie: MovieClip, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .height(150.dp)
            .clickable {
                navController.navigate(
                    Screen.VideoViewScreen.route
                            + "?videoId=${movie.video_id}&title=${movie.movie_name}&year=${movie.movie_year}"
                )
            }
    ) {
        AsyncImage(
            model = movie.thumbnail,
            contentDescription = movie.movie_name,
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)
        ) {
            Text(text = movie.movie_name, fontWeight = FontWeight.ExtraBold, color = Color.White)
        }
    }
}
