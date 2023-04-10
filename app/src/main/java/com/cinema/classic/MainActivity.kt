package com.cinema.classic

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cinema.classic.presentation.main_list.MainScreen
import com.cinema.classic.presentation.ui.theme.ClassicTheme
import com.cinema.classic.presentation.util.Screen
import com.cinema.classic.presentation.video_detail.VideoViewScreen
import dagger.hilt.android.AndroidEntryPoint

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
                            MainScreen(navController)
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
                            VideoViewScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
