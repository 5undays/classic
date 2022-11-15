package com.cinema.classic

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import com.cinema.classic.data.movie.impl.MovieService
import com.cinema.classic.databinding.FragmentContainerYoutubeBinding
import com.cinema.classic.fragment.YoutubeFragment
import com.cinema.classic.model.MainActivityViewModel
import com.cinema.classic.model.NaverMovie
import com.cinema.classic.model.NaverResult
import com.cinema.classic.model.Snippet
import com.cinema.classic.ui.article.ArticleScreen
import com.cinema.classic.ui.theme.ClassicTheme2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

private lateinit var viewModel: MainActivityViewModel

class YoutubeActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
            val video_id = intent.getStringExtra("video_id")
            val title = intent.getStringExtra("title")?.split("/")
            if (video_id != null && title != null) {
                //contentView(video_id = video_id, title = title[0] + "\n" + title[1])
                test(title.get(0).split("(").get(0), video_id)
                //HelloScreen()
                //Greeting()

            }
        }
    }
}


@Composable
fun test(title: String, video_id: String) {
    val data1 by viewModel.data.observeAsState(
        NaverMovie(
            title = "",
            link = "",
            image = "",
            subtitle = "",
            pubDate = "",
            director = "",
            actor = "",
            userRating = ""
        )
    )
    var name by rememberSaveable { mutableStateOf("") }
    Retrofit.api.get2(stringResource(R.string.naver_api_url), title)
        .enqueue(object : Callback<NaverResult> {
            override fun onResponse(call: Call<NaverResult>, response: Response<NaverResult>) {
                val n: NaverMovie? = response.body()?.items?.get(0)

                if (n != null) {
                    viewModel.test(n)
                }
            }

            override fun onFailure(call: Call<NaverResult>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    ArticleScreen(video_id, data1, {}, title, true, {}, false, {})
}

@Composable
fun HelloScreen() {
    var name by rememberSaveable { mutableStateOf("") }

    HelloContent(name = name, onNameChange = { name = it })
}

@Composable
fun HelloContent(name: String, onNameChange: (String) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Hello, $name",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.h5
        )
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Name") }
        )
    }
}

@Composable
fun YoutubePlayerView(video_id: String) {
    AndroidViewBinding(FragmentContainerYoutubeBinding::inflate) {
        val f = fragmentContainerView.getFragment<YoutubeFragment>()
        f.initialVideo(video_id)
    }
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
fun contentView(video_id: String, title: String) {
    //val data = remember {}
    ClassicTheme2() {
        LazyColumn(content = {
            item {
                YoutubePlayerView(video_id)
            }
            item {
                getMovieData(title)
            }
        })
    }
}

@Composable
fun getMovieData(title: String) {
    Card(modifier = Modifier.padding(4.dp)) {
        Column() {
            Text(text = title, fontWeight = FontWeight.ExtraBold)
        }
    }
}

@Preview("제목")
@Composable
fun prevGetMovieData() {
    ClassicTheme2 {
        getMovieData(title = "골목안 풍경 (1962) \n What Happens in a Alley")
    }
}