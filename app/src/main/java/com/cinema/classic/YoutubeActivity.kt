package com.cinema.classic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.fragment.app.FragmentActivity
import com.cinema.classic.databinding.MyFragmentLayoutBinding
import com.cinema.classic.fragment.FragmentExampleFragment
import com.cinema.classic.model.YoutubeRepo
import com.cinema.classic.theme.JetnewsTheme
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.android.synthetic.main.my_fragment_layout.*


class YoutubeActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FragmentInComposeExample()
        }
    }
}

@Composable
fun FragmentInComposeExample() {
    AndroidViewBinding(MyFragmentLayoutBinding::inflate) {
        val myFragment = fragmentContainerView.getFragment<FragmentExampleFragment>()
        // ...
    }
}

//@Preview("Post Item")
//@Composable
//private fun PostItemPreview() {
//    FragmentInComposeExample()
//}