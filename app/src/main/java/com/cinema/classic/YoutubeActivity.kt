package com.cinema.classic

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cinema.classic.databinding.FragmentContainerViewpagerBinding
import com.cinema.classic.databinding.FragmentContainerYoutubeBinding
import com.cinema.classic.fragment.ViewpagerFragment
import com.cinema.classic.fragment.YoutubeFragment
import com.cinema.classic.viewmodels.MainActivityViewModel
import com.cinema.classic.model.NaverMovie
import com.cinema.classic.model.NaverResult
import com.cinema.classic.ui.article.ArticleScreen
import com.cinema.classic.ui.article.PostContent
import com.cinema.classic.ui.theme.ClassicTheme2
import com.cinema.classic.viewmodels.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class YoutubeActivity : FragmentActivity() {
    private val viewmodel: MainActivityViewModel by viewModels()
    private var searchJob: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val video_id = intent.getStringExtra("video_id")
            val title = intent.getStringExtra("title")?.split("/")
            if (video_id != null && title != null) {
                movieData(title.get(0).split("(").get(0), video_id,viewmodel)
                search(title.get(0).split("(").get(0))
            }
        }
    }

    private fun search(query: String) {
        // Make sure we cancel the previous job before creating a new one
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewmodel.getMovieDetail(query)
        }
    }
}


@Composable
fun movieData(title:String, video_id: String,viewModel: MainActivityViewModel) {
    val data1 by viewModel.data.observeAsState()
    data1?.let {
        PostContent(
            video_id = video_id,
            post = it
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
fun PagerView() {
    AndroidViewBinding(FragmentContainerViewpagerBinding::inflate) {
        val f = fragmentContainerViewpager.getFragment<ViewpagerFragment>()
    }
}

@Preview("제목")
@Composable
fun prevGetMovieData() {
    ClassicTheme2 {
        //getMovieData(title = "골목안 풍경 (1962) \n What Happens in a Alley")
    }
}