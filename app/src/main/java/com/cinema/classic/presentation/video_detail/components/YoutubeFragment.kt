package com.cinema.classic.presentation.video_detail.components

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cinema.classic.data.local.dto.MovieClip
import com.cinema.classic.databinding.FragmentYoutubeBinding
import com.cinema.classic.presentation.video_detail.VideoViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class YoutubeFragment : Fragment() {
    lateinit var binding: FragmentYoutubeBinding
    lateinit var playstate: PlayerConstants.PlayerState
    var seconds: Float = 0.0f
    lateinit var id: String
    lateinit var video_name: String
    var year: Int = 0

    private val viewModel: VideoViewModel by viewModels()
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
        playstate = PlayerConstants.PlayerState.UNKNOWN
        return binding.root
    }

    fun initialVideo(video_id: String, start_second: Float, title: String, movie_year: Int) {
        video_name = title
        id = video_id
        year = movie_year
        val listener: YouTubePlayerListener = object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val defaultPlayerUiController =
                    DefaultPlayerUiController(binding.youtubePlayerView, youTubePlayer)
                binding.youtubePlayerView.setCustomPlayerUi(defaultPlayerUiController.rootView)
                youTubePlayer.loadVideo(video_id, start_second)
            }

            override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
                seconds = second
            }

            override fun onStateChange(
                youTubePlayer: YouTubePlayer,
                state: PlayerConstants.PlayerState
            ) {
                if (state == PlayerConstants.PlayerState.PLAYING && playstate != PlayerConstants.PlayerState.PLAYING) {
                    playstate = state
                }
            }
        }
        val fullScreenListener: YouTubePlayerFullScreenListener = object :
            YouTubePlayerFullScreenListener {
            override fun onYouTubePlayerEnterFullScreen() {
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }

            override fun onYouTubePlayerExitFullScreen() {
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            }
        }
        binding.youtubePlayerView.addFullScreenListener(fullScreenListener)
        // disable iframe ui
        val options: IFramePlayerOptions = IFramePlayerOptions.Builder().controls(0).build()
        binding.youtubePlayerView.initialize(listener, options)
    }

    override fun onStop() {
        CoroutineScope(Dispatchers.IO).launch {
            //viewModel.insertMovieClip(movieClip = MovieClip(id, seconds, video_name, year))
        }
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.youtubePlayerView.release()
    }
}