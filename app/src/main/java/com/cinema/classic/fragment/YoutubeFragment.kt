package com.cinema.classic.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.cinema.classic.data.interest.MovieClip
import com.cinema.classic.databinding.FragmentYoutubeBinding
import com.cinema.classic.viewmodels.VideoViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class YoutubeFragment : Fragment() {
    private lateinit var binding: FragmentYoutubeBinding
    lateinit var movieClip: MovieClip
    private val viewModel: VideoViewModel by viewModels()
    private var searchJob: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentYoutubeBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun initialVideo(video_id: String) {
        val listener: YouTubePlayerListener = object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val defaultPlayerUiController =
                    DefaultPlayerUiController(binding.youtubePlayerView, youTubePlayer)
                binding.youtubePlayerView.setCustomPlayerUi(defaultPlayerUiController.rootView);
                youTubePlayer.loadVideo(video_id, 0f)
            }

            override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
                movieClip = MovieClip(video_id, second)
            }
        }
        // disable iframe ui
        val options: IFramePlayerOptions = IFramePlayerOptions.Builder().controls(0).build()
        binding.youtubePlayerView.initialize(listener, options)
    }

    override fun onStop() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.insertMovieClip(movieClip = movieClip)
        }
        super.onStop()
    }
    override fun onDestroy() {
        //searchJob?.cancel()
//        lifecycleScope.launch {
//            viewModel.insertMovieClip(movieClip = movieClip)
//        }
        super.onDestroy()
        binding.youtubePlayerView.release()
    }
}