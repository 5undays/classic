package com.cinema.classic

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateListOf
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
import com.cinema.classic.model.Item
import com.cinema.classic.model.Snippet
import com.cinema.classic.model.Youtube
import com.cinema.classic.model.YoutubeRepo
import com.cinema.classic.theme.ClassicTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.cinema.classic.Retrofit.api

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Home(stringResource(R.string.google_api_url))
        }
    }
}

fun initializeMovieData(url:String, result: MutableList<Item>) {
    api.get(url,"snippet", "UCvH6u_Qzn5RQdz9W198umDw", "date")
        .enqueue(object : Callback<Youtube> {
            override fun onResponse(call: Call<Youtube>, response: Response<Youtube>) {
                var list: List<Item>? = response.body()?.items
                if (list != null) {
                    for (i in 0 until list.size) {
                        // on below line we are adding data to course list.
                        list?.get(i)?.let { result.add(it) }
                    }
                }
            }

            override fun onFailure(call: Call<Youtube>, t: Throwable) {
            }
        })
}

@Composable
fun Home(url: String) {
    val posts = remember { mutableStateListOf<Item>() }
    initializeMovieData(url, posts)

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
                items(posts) { post ->
                    PostItem(post = post)
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
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
private fun PostMetadata(
    post: Snippet,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(
            text = post.publishedAt,
            style = MaterialTheme.typography.body2,
            modifier = modifier
        )
    }
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
                val intent = Intent(ctx, YoutubeActivity::class.java)
                intent.putExtra("title", post.snippet.title)
                intent.putExtra("video_id", post.id.videoId)
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
