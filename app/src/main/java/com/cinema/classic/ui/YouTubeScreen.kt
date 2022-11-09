//package com.cinema.classic.ui
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import com.google.android.youtube.player.*
//
//class VideoPlayerFragment : Fragment() {
//
//    private var link: String? = null
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
////        arguments?.let {
////            val safeArgs = VideoPlayerFragmentArgs.fromBundle(it)
////            link = safeArgs.link
////        }
//
////        val youtubePlayerFragment = YouTubePlayerSupportFragmentXKt.newInstance()
////        val transaction = childFragmentManager.beginTransaction()
////        transaction.replace(R.id.player, youtubePlayerFragment).commit()
//        val playerView : YouTubePlayerSupportFragmentXKt = supportFragmentManager.findFragmentById(R.id.fragPlayer) as YouTubePlayerSupportFragmentX
//        playerView.initialize(getString(R.string.YOUTUBE_API_KEY), this)
//
//        youtubePlayerFragment.initialize(resources.getString(R.string.API_KEY), object : YouTubePlayer.OnInitializedListener {
//            override fun onInitializationSuccess(
//                p0: YouTubePlayer.Provider?,
//                p1: YouTubePlayer?,
//                p2: Boolean
//            ) {
//                p1?.loadVideo(link)
//            }
//
//            override fun onInitializationFailure(
//                p0: YouTubePlayer.Provider?,
//                p1: YouTubeInitializationResult?
//            ) {
//            }
//        })
//
//        return inflater.inflate(R.layout.fragment_video_player, container, false)
//    }
//}