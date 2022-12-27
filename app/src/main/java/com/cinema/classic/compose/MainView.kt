package com.cinema.classic.compose

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
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
import com.cinema.classic.data.MovieClip
import com.cinema.classic.data.Item
import com.cinema.classic.data.NaverMovie
import com.cinema.classic.data.YoutubeRepo
import com.cinema.classic.compose.theme.ClassicTheme
import com.cinema.classic.viewmodels.MainViewModel

@Composable
fun Home(viewModel: MainViewModel) {
    val list = viewModel.uploadList.observeAsState()
    val last = viewModel.lastClip.observeAsState()
    val data2 = viewModel.data2.observeAsState()

    ClassicTheme {
        Scaffold(
            topBar = { AppBar() }
        ) { innerPadding ->
            LazyColumn(contentPadding = innerPadding) {
                last.value?.let {
                    item {
                        Header(stringResource(R.string.popular))
                    }
                    item {
                        data2.value?.let {
                            LastMovie(data2, last.value!!, viewModel)
                        }
                    }
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

@Composable
fun LastMovie(movie: State<NaverMovie?>, clip: MovieClip, viewModel: MainViewModel) {
    val ctx = LocalContext.current
    Card(
        modifier = Modifier
            .clickable {
                val intent = Intent(ctx, YoutubeActivity::class.java).apply {
                    putExtra("title", clip.movie_name)
                    putExtra("year", clip.movie_year)
                    putExtra("video_id", clip.video_id)
                }
                ctx.startActivity(intent)
            }
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .height(150.dp)
    ) {
        AsyncImage(
            model = movie.value!!.image,
            contentDescription = movie.value!!.director,
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
