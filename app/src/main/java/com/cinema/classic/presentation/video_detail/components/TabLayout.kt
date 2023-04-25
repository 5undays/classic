package com.cinema.classic.presentation.video_detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cinema.classic.domain.model.Kmdb
import com.cinema.classic.domain.model.MovieClip
import com.cinema.classic.presentation.video_detail.VideoViewEvent
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

val pages = listOf("PLOT", "BOOKMARK")

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayout(movie: Kmdb, movieClips: List<MovieClip>, onEvent: (VideoViewEvent) -> Unit) {

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }
        ) {
            pages.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(index)
                        }
                    },
                    modifier = Modifier.height(IntrinsicSize.Min)
                )
            }
        }
        HorizontalPager(
            count = pages.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            if (page == 0) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    movie?.let {
                        Text(
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(10.dp),
                            text = movie.plot,
                            style = MaterialTheme.typography.body2
                        )
                    }
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxHeight()) {
                    movieClips.let {
                        items(movieClips) { post ->
                            MovieClipItem(post = post, onToggleClip = {
                                onEvent(VideoViewEvent.DeleteMovieClip(post))
                            })
                        }
                    }
                }
            }
        }
    }
}
