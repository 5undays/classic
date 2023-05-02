package com.cinema.classic

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.cinema.classic.presentation.main_list.MainScreen
import com.cinema.classic.presentation.main_list.MainViewModel
import com.cinema.classic.presentation.ui.theme.ClassicTheme
import com.cinema.classic.presentation.util.Screen
import com.cinema.classic.presentation.video_detail.VideoViewModel
import com.cinema.classic.presentation.video_detail.VideoViewScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : FragmentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
//        window.decorView.apply {
//            systemUiVisibility =
//                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
//        }
        setContent {
            ClassicTheme {
                Surface {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MainScreen.route
                    ) {
                        composable(route = Screen.MainScreen.route) {
                            val viewModel = hiltViewModel<MainViewModel>()
                            val mainVideos = viewModel.mainVideos.collectAsLazyPagingItems()
                            val movieClips = viewModel.lastClip.observeAsState()
                            MainScreen(navController, mainVideos, movieClips.value)
                        }
                        composable(route = Screen.VideoViewScreen.route + "?year={year}&title={title}&videoId={videoId}",
                            arguments = listOf(
                                navArgument(name = "videoId") {
                                    type = NavType.StringType
                                    defaultValue = ""
                                }, navArgument(name = "year") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }, navArgument(name = "title") {
                                    type = NavType.StringType
                                    defaultValue = ""
                                }
                            )) {
                            val viewModel = hiltViewModel<VideoViewModel>()
                            val videoId = viewModel.videoId
                            val movie = viewModel.data.value.movie
                            val movieClips = viewModel.data.value.movieClips
                            VideoViewScreen(
                                navController = navController,
                                movie = movie,
                                videoId = videoId,
                                movieClips = movieClips,
                                onEvent = viewModel::onEvent
                            )
                        }
                    }
                }
            }
        }
    }
}
