package com.cinema.classic

import android.content.Intent
import android.content.res.Resources.Theme
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import androidx.lifecycle.MutableLiveData
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.cinema.classic.model.*
import com.cinema.classic.theme.JetnewsTheme
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragmentXKt
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Home()
        }
    }
}

fun InitializeMovieData(result: MutableList<Item>) {
    com.cinema.classic.Retrofit.api.get("snippet", "UCvH6u_Qzn5RQdz9W198umDw", "date")
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
fun Home() {
    val posts = remember { mutableStateListOf<Item>() }
    InitializeMovieData(posts)

    JetnewsTheme {
        Scaffold(
            topBar = { AppBar() }
        ) { innerPadding ->
            LazyColumn(contentPadding = innerPadding) {
                item {
                    Header(stringResource(R.string.korean_film))
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
    var title = post.snippet.title.split("/")
    Card(
        modifier = modifier
            .clickable { /**/ }
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
    //val post = remember { PostRepo.getFeaturedPost() }
    val post = remember {
        YoutubeRepo.getFeaturedPost()
    }
    JetnewsTheme {
        Surface {
            PostItem(post = post)
        }
    }
}

@Composable
fun YouTubeScreen1() {
    val videoId = "FHZ6bI3zb4M"
    val ctx = LocalContext.current
    AndroidView(
        factory = {
            val fm = (ctx as AppCompatActivity).supportFragmentManager
            val view = FragmentContainerView(it).apply {
                id = androidx.fragment.R.id.fragment_container_view_tag
            }
            val fragment = YouTubePlayerSupportFragmentXKt().apply {
                initialize(
                    "AIzaSyDcBkLYiDrMo2RsNsCds4BKKpoJbfrthrQ", // api key
                    object : YouTubePlayer.OnInitializedListener {
                        override fun onInitializationFailure(
                            provider: YouTubePlayer.Provider,
                            result: YouTubeInitializationResult
                        ) {
                            Toast.makeText(
                                context,
                                "Error initializing video",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        override fun onInitializationSuccess(
                            provider: YouTubePlayer.Provider,
                            player: YouTubePlayer,
                            wasRestored: Boolean
                        ) {
                            // TODO closing this screen when the player is in fullscreen
                            //  is making the app keep in landscape. Disabling for now.
                            player.setShowFullscreenButton(false)
                            if (!wasRestored) {
                                player.cueVideo(videoId)
                            }
                        }
                    },
                )
            }
            fm.commit {
                setReorderingAllowed(true)
                add(androidx.fragment.R.id.fragment_container_view_tag, fragment)
            }
            view
        },
        modifier = Modifier.fillMaxSize()
    )
}