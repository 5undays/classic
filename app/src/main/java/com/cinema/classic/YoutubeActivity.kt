package com.cinema.classic

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.fragment.app.FragmentActivity
import com.cinema.classic.databinding.FragmentContainerYoutubeBinding
import com.cinema.classic.fragment.YoutubeFragment


class YoutubeActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YoutubePlayerView()
        }
    }
}

@Composable
fun YoutubePlayerView() {
    AndroidViewBinding(FragmentContainerYoutubeBinding::inflate) {
        val myFragment = fragmentContainerView.getFragment<YoutubeFragment>()
        // ...
    }
}
