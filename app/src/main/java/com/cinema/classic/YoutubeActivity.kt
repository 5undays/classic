package com.cinema.classic

import android.os.Bundle
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.youtube_layout.*

class YoutubeActivity : YouTubeBaseActivity() {

    lateinit var youtubePlayerInit: YouTubePlayer.OnInitializedListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.youtube_layout)
        intent.getStringExtra("video_id")?.let { initUI(it) }
    }

    private fun initUI(video_id:String) {
        youtubePlayerInit = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, youtubePlayer: YouTubePlayer?, p2: Boolean) {
                youtubePlayer?.loadVideo(video_id)
            }

            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
                Toast.makeText(applicationContext, "Something went wrong !! ", Toast.LENGTH_SHORT).show()
            }
        }
        youtubePlayer.initialize(getString(R.string.GOOGLE_API_KEY), youtubePlayerInit)
    }

}