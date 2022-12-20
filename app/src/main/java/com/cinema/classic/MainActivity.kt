package com.cinema.classic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.cinema.classic.compose.Home
import com.cinema.classic.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewmodel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Home(viewmodel)
            viewmodel.lastClip.observe(this) { lastClip ->
                viewmodel.getMovieDetail(lastClip.movie_name, lastClip.movie_year)
            }
        }
    }
}
