package com.cinema.classic.presentation.video_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cinema.classic.domain.model.Kmdb

@Composable
fun MovieDetail(movie: Kmdb) {
    Column {
        Text(
            movie.title + ", "+ movie.year,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            movie.director,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(start = 10.dp)
        )
        Text(
            movie.actors,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(start = 10.dp)
        )
        Spacer(Modifier.height(16.dp))
    }
}

//@Preview
//@Composable
//fun MovieDetail() {
//    Column {
//        Text(
//            "영화이름영화이름영화이름영화이름, 1970",
//            style = MaterialTheme.typography.h4,
//            modifier = Modifier.padding(10.dp)
//        )
//        Text(
//            "감독이름감독이름",
//            style = MaterialTheme.typography.body1,
//            modifier = Modifier.padding(start = 10.dp)
//        )
//        Text(
//            "배우이름배우이름배우이름",
//            style = MaterialTheme.typography.body2,
//            modifier = Modifier.padding(start = 10.dp)
//        )
//        Spacer(Modifier.height(16.dp))
//    }
//}