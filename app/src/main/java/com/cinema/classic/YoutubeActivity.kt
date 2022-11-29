package com.cinema.classic

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.cinema.classic.ui.article.movieData
import com.cinema.classic.viewmodels.VideoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class YoutubeActivity : FragmentActivity() {
    private val viewModel: VideoViewModel by viewModels()

    private var searchJob: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val video_id = intent.getStringExtra("video_id")
            val title = intent.getStringExtra("title")?.split("/")
            if (video_id != null && title != null) {
                movieData(video_id, viewModel)
                search(title.get(0).split("(").get(0), video_id)
            }
        }
    }

    private fun search(title: String,videoId: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.getMovieDetail(title)
            viewModel.getMovieClips(videoId)
        }
    }
}


