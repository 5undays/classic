package com.cinema.classic.presentation.util

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object VideoViewScreen : Screen("video_view_screen")
}
