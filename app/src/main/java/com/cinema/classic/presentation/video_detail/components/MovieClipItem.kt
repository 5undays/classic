package com.cinema.classic.presentation.video_detail.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cinema.classic.domain.model.MovieClip
import com.cinema.classic.presentation.ui.utils.BookmarkButton
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
@Composable
fun MovieClipItem(
    post: MovieClip,
    onToggleClip: () -> Unit,
    modifier: Modifier = Modifier
) {
    val sdf = SimpleDateFormat("HH:mm:ss")
    sdf.timeZone = TimeZone.getTimeZone("UTC")

    Card(
        modifier = modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .height(60.dp)
    ) {
        Row(
            modifier = modifier
                .padding(vertical = 8.dp, horizontal = 8.dp)
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = sdf.format(post.time * 1000),
                modifier = Modifier.weight(0.5f)
            )
            Text(
                text = SimpleDateFormat(
                    "yyyy-MM-dd",
                    Locale.getDefault()
                ).format(post.reg_date.timeInMillis),
                color = Color.Black,
                modifier = Modifier.wrapContentSize()
            )
            BookmarkButton(onClick = onToggleClip)
        }
    }
}
