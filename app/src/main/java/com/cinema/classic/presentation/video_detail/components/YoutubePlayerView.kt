package com.cinema.classic.presentation.video_detail.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.cinema.classic.presentation.video_detail.VideoViewEvent
import com.cinema.classic.presentation.video_detail.VideoViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController

@Composable
fun YoutubeScreen(
    videoId: String,
    modifier: Modifier = Modifier,
    viewModel: VideoViewModel = hiltViewModel()
) {
    val ctx = LocalContext.current
    val startSeconds = 0f;
    AndroidView(factory = {
        var view = YouTubePlayerView(it)
        val fragment = view.addYouTubePlayerListener(
            object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    view.setCustomPlayerUi(DefaultPlayerUiController(view, youTubePlayer).rootView)
                    youTubePlayer.loadVideo(videoId, startSeconds)
                }

                override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
                    super.onCurrentSecond(youTubePlayer, second)
                    viewModel.onEvent(VideoViewEvent.GetCurrentTime(second))
                }
            }
        )
        view.addFullScreenListener(object : YouTubePlayerFullScreenListener {
            override fun onYouTubePlayerEnterFullScreen() {
                //viewModel.onEvent(VideoViewEvent.SetOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE))
            }

            override fun onYouTubePlayerExitFullScreen() {
                //viewModel.onEvent(VideoViewEvent.SetOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED))
            }
        })
        view
    })
}


//@Composable
//fun YoutubePlayerView(
//    title: String,
//    video_id: String,
//    year: Int,
//    viewModel: VideoViewModel
//) {
//    val data2 = viewModel.data.value.movieClips
//    AndroidViewBinding(FragmentContainerYoutubeBinding::inflate) {
//        val f = fragmentContainerView.getFragment<YoutubeFragment>().apply {
//            if (playstate != PlayerConstants.PlayerState.PLAYING) {
//                initialVideo(
//                    video_id,
//                    0f,
//                    title,
//                    year
//                )
//            }
//        }
////        if (f.playstate != PlayerConstants.PlayerState.PLAYING) {
////            if (data2.isNotEmpty()) {
////                f.initialVideo(
////                    video_id,
////                    data2.get(0).time,
////                    title,
////                    Integer.parseInt(year)
////                )
////            } else {
////                f.initialVideo(
////                    video_id,
////                    0f,
////                    title.replace("<b>", "").replace("</b>", ""),
////                    Integer.parseInt(year)
////                )
////            }
////        }
//    }
//}