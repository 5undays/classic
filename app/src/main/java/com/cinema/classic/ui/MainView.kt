package com.cinema.classic.ui

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cinema.classic.R
import com.cinema.classic.YoutubeActivity
import com.cinema.classic.model.Item
import com.cinema.classic.model.YoutubeRepo
import com.cinema.classic.ui.theme.ClassicTheme
import com.cinema.classic.viewmodels.MainViewModel

@Composable
fun Home(viewModel: MainViewModel) {
    var list = viewModel.uploadList.observeAsState()

    ClassicTheme {
        Scaffold(
            topBar = { AppBar() }
        ) { innerPadding ->
            LazyColumn(contentPadding = innerPadding) {
                item {
                    Header(stringResource(R.string.popular))
                }
                item {
                    Header(stringResource(R.string.korean_film))
                }
                list.value?.let {
                    items(list.value!!) { post ->
                        PostItem(post = post)
                    }
                }
            }
        }
    }
}

@Composable
private fun AppBar() {
    TopAppBar(
        navigationIcon = {
            Icon(
                imageVector = Icons.Rounded.Home,
                contentDescription = null,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        },
        title = {
            Text(text = stringResource(R.string.app_title))
        },
        backgroundColor = MaterialTheme.colors.primarySurface
    )
}

@Composable
fun Header(
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.1f),
        contentColor = MaterialTheme.colors.primary,
        modifier = modifier.semantics { heading() }
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun PostItem(
    post: Item,
    modifier: Modifier = Modifier
) {
    var title = remember {
        post.snippet.title.split("/")
    }
    val ctx = LocalContext.current

    Card(
        modifier = modifier
            .clickable {
                val intent = Intent(ctx, YoutubeActivity::class.java).apply {
                    putExtra("title", post.snippet.title)
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
            Text(text = title[1], color = Color.White)
        }
    }
}


@Preview("Post Item")
@Composable
private fun PostItemPreview() {
    val post = remember {
        YoutubeRepo.getFeaturedPost()
    }
    ClassicTheme {
        Surface {
            PostItem(post = post)
        }
    }
}
