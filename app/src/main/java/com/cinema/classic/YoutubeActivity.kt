package com.cinema.classic

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.viewmodel.compose.viewModel
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

class YoutubeActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val video_id = intent.getStringExtra("video_id")
            val title = intent.getStringExtra("title")?.split("/")
            if (video_id != null && title != null) {
                movieData(title.get(0).split("(").get(0), video_id)
            }
        }
    }
}


@Composable
fun movieData(title: String, video_id: String, viewModel: MainActivityViewModel = viewModel()) {
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
fun YoutubePlayerView(video_id: String) {
    AndroidViewBinding(FragmentContainerYoutubeBinding::inflate) {
        val f = fragmentContainerView.getFragment<YoutubeFragment>()
        f.initialVideo(video_id)
    }
}


@Preview("제목")
@Composable
fun prevGetMovieData() {
    ClassicTheme2 {
        //getMovieData(title = "골목안 풍경 (1962) \n What Happens in a Alley")
    }
}