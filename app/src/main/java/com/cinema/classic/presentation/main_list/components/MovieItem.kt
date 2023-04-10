package com.cinema.classic.presentation.main_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.material.Text
import com.cinema.classic.domain.model.Movie

@Composable
fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .height(150.dp)
    ) {
        AsyncImage(
            model = movie.thumbnail,
            contentDescription = movie.description,
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = modifier.padding(vertical = 8.dp, horizontal = 8.dp)
        ) {
            Text(text = movie.title, fontWeight = FontWeight.ExtraBold, color = Color.White)
//            if (title.size > 1) {
//                Text(text = title[1], color = Color.White)
//            }
        }
    }
}