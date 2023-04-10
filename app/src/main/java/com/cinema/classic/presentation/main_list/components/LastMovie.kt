package com.cinema.classic.presentation.main_list.components

import android.content.Intent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import coil.compose.AsyncImage
import com.cinema.classic.YoutubeActivity
import com.cinema.classic.domain.model.MovieClip
import com.cinema.classic.domain.model.NaverMovie

@Composable
fun LastMovie(movie: NaverMovie, clip: MovieClip) {
    val ctx = LocalContext.current
    Card(
        modifier = Modifier
            .clickable {
                ctx.startActivity(Intent().apply {
                    setClass(ctx, YoutubeActivity::class.java)
                    putExtras(
                        bundleOf(
                            "title" to clip.movie_name,
                            "year" to clip.movie_year,
                            "video_id" to clip.video_id
                        )
                    )
                })
            }
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .height(150.dp)
    ) {
        AsyncImage(
            model = movie.image,
            contentDescription = movie.director,
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)
        ) {
            Text(text = clip.movie_name, fontWeight = FontWeight.ExtraBold, color = Color.White)
        }
    }
}