package com.cinema.classic

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.cinema.classic.compose.movieData
import com.cinema.classic.viewmodels.VideoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class YoutubeActivity : FragmentActivity() {
    private val viewModel: VideoViewModel by viewModels()

    //private var searchJob: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.decorView.apply {
            systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        setContent {
            movieData(viewModel)
            //search(title, video_id, year)
        }
    }

//    private fun search(title: String, videoId: String, year: Int) {
//        searchJob?.cancel()
//        searchJob = lifecycleScope.launch {
//            viewModel.getMovieDetail(title, year, videoId)
//            viewModel.getMoviePlot(title)
//        }
//    }
}


