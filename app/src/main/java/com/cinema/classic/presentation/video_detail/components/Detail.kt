package com.cinema.classic.presentation.video_detail.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cinema.classic.domain.model.NaverMovie

@Composable
fun detail(it: NaverMovie) {
    Spacer(Modifier.height(16.dp))
    Text(
        it.title.replace("<b>", "").replace("</b>", "") + ", " + it.pubDate,
        style = MaterialTheme.typography.h4,
        modifier = Modifier.padding(start = 10.dp)
    )
    Spacer(Modifier.height(8.dp))
    if (it.director != "") {
        val directors =
            it.director.replace("|", ",").substring(0, it.director.length - 1)
        Text(
            directors,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
    if (it.actor != "") {
        val actors = it.actor.replace("|", ",").substring(0, it.actor.length - 1)
        Text(
            actors,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
    Spacer(Modifier.height(16.dp))
}