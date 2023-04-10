package com.cinema.classic.presentation.video_detail.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.cinema.classic.databinding.FragmentContainerYoutubeBinding
import com.cinema.classic.presentation.video_detail.VideoViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants

@Composable
fun YoutubePlayerView(title: String, video_id: String, year: String, viewModel: VideoViewModel) {
    val data2 = viewModel.data.value.movieClips
    if (data2 != null) {
        AndroidViewBinding(FragmentContainerYoutubeBinding::inflate) {
            val f = fragmentContainerView.getFragment<YoutubeFragment>()
            if (f.playstate != PlayerConstants.PlayerState.PLAYING) {
                if (data2?.size!! > 0) {
                    f.initialVideo(
                        video_id,
                        data2!!.get(0).time,
                        title.replace("<b>", "").replace("</b>", ""),
                        Integer.parseInt(year)
                    )
                } else {
                    f.initialVideo(
                        video_id,
                        0f,
                        title.replace("<b>", "").replace("</b>", ""),
                        Integer.parseInt(year)
                    )
                }
            }
        }
    }
}