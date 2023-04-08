package com.cinema.classic.presentation.main_list.components

import android.content.Intent
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cinema.classic.YoutubeActivity
import androidx.compose.material.Text
import com.cinema.classic.domain.model.Item

@Composable
fun PostItem(
    post: Item,
    modifier: Modifier = Modifier
) {
    var title = remember {
        post.snippet.title.split("/")
    }
    val ctx = LocalContext.current
    val intentData = title[0].split("(")

    Card(
        modifier = modifier
            .clickable {
                val intent = Intent(ctx, YoutubeActivity::class.java).apply {
                    putExtra("title", intentData[0])
                    putExtra(
                        "year",
                        Integer.parseInt(
                            intentData[1]
                                .replace(")", "")
                                .trim()
                        )
                    )
                    putExtra("video_id", post.id.videoId)
                }
                ctx.startActivity(intent)
            }
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .height(150.dp)
    ) {
        AsyncImage(
            model = post.snippet.thumbnails.high.url,
            contentDescription = post.snippet.description,
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = modifier.padding(vertical = 8.dp, horizontal = 8.dp)
        ) {
            Text(text = title[0], fontWeight = FontWeight.ExtraBold, color = Color.White)
            if (title.size > 1) {
                Text(text = title[1], color = Color.White)
            }
        }
    }
}