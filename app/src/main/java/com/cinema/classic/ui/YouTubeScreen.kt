package com.cinema.classic.ui

//import android.view.View
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//import androidx.appcompat.app.AppCompatActivity
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.viewinterop.AndroidView
//import androidx.fragment.app.FragmentActivity
//import androidx.fragment.app.FragmentContainerView
//import androidx.fragment.app.FragmentManager
//import androidx.fragment.app.commit
//import com.cinema.classic.R
//import com.google.android.youtube.player.YouTubeBaseActivity
//import com.google.android.youtube.player.YouTubeInitializationResult
//import com.google.android.youtube.player.YouTubePlayer
//import com.google.android.youtube.player.YouTubePlayerSupportFragmentXKt
//
//@Composable
//fun YouTubeScreen() {
//    val videoId = "FHZ6bI3zb4M"
//    val ctx = LocalContext.current
//    AndroidView(
//        factory = {
//            val view = FragmentContainerView(it).apply {
//                id = androidx.fragment.R.id.youtube_layout
//            }
//            val fragment = YouTubePlayerSupportFragmentXKt().apply {
//                initialize(
//                    "AIzaSyDcBkLYiDrMo2RsNsCds4BKKpoJbfrthrQ", // api key
//                    object : YouTubePlayer.OnInitializedListener {
//                        override fun onInitializationFailure(
//                            provider: YouTubePlayer.Provider,
//                            result: YouTubeInitializationResult
//                        ) {
//                            Toast.makeText(
//                                context,
//                                "Error initializing video",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//
//                        override fun onInitializationSuccess(
//                            provider: YouTubePlayer.Provider,
//                            player: YouTubePlayer,
//                            wasRestored: Boolean
//                        ) {
//                            // TODO closing this screen when the player is in fullscreen
//                            //  is making the app keep in landscape. Disabling for now.
//                            player.setShowFullscreenButton(false)
//                            if (!wasRestored) {
//                                player.cueVideo(videoId)
//                            }
//                        }
//                    },
//                )
//            }
//            fm.commit {
//                setReorderingAllowed(true)
//                add(androidx.fragment.R.id.fragment_container_view_tag, fragment)
//            }
//            view
//        },
//        modifier = Modifier.fillMaxSize()
//    )
//}