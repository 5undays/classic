package com.cinema.classic

import android.os.Bundle
import android.view.View
import android.view.WindowManager
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
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.decorView.apply {
            systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        setContent {
            val video_id = intent.getStringExtra("video_id")
            val title = intent.getStringExtra("title")?.split("/")
            val kor = title?.get(0)?.split("(")
            if (video_id != null && kor != null) {
                movieData(video_id, viewModel)
                search(kor.get(0), video_id, Integer.parseInt(kor.get(1).replace(")", "").trim()))
            }
        }
    }

    private fun search(title: String, videoId: String, year: Int) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.getMovieDetail(title, year, videoId)
            viewModel.getMoviePlot(title)
        }
    }
}


