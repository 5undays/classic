package com.cinema.classic.presentation.video_detail.components

import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.cinema.classic.presentation.video_detail.VideoViewEvent
import com.cinema.classic.presentation.video_detail.VideoViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.customui.DefaultPlayerUiController
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YoutubeScreen(
    videoId: String,
    viewModel: VideoViewModel = hiltViewModel()
) {
    val options: IFramePlayerOptions =
        IFramePlayerOptions.Builder().controls(0).fullscreen(1).build()
    var view = YouTubePlayerView(LocalContext.current)
    view.enableAutomaticInitialization = false
    val listener = object : AbstractYouTubePlayerListener() {
        override fun onReady(youTubePlayer: YouTubePlayer) {
            super.onReady(youTubePlayer)
            view.setCustomPlayerUi(DefaultPlayerUiController(view, youTubePlayer).rootView)
            youTubePlayer.loadVideo(videoId, 0f)
//            if (viewModel.data.value.movieClips.isNotEmpty()) {
//                youTubePlayer.seekTo(viewModel.data.value.movieClips.last().time)
//            } else {
//
//            }
        }

        override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
            super.onCurrentSecond(youTubePlayer, second)
            viewModel.onEvent(VideoViewEvent.GetCurrentTime(second))

        }
    }
    view.addFullscreenListener(object : FullscreenListener {
        override fun onEnterFullscreen(fullscreenView: View, exitFullscreen: () -> Unit) {
            TODO("Not yet implemented")
        }

        override fun onExitFullscreen() {
            TODO("Not yet implemented")
        }
    })
    view.initialize(listener, options)
    AndroidView(factory = { view })
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