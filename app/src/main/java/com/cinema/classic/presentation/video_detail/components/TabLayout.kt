package com.cinema.classic.presentation.video_detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cinema.classic.presentation.video_detail.VideoViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.items
import com.cinema.classic.presentation.video_detail.VideoViewEvent

@OptIn(ExperimentalPagerApi::class)
@Composable
fun tabLayout(viewModel: VideoViewModel) {
    val list = viewModel.data.value.movieClips
    val plot = viewModel.data.value.plot

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
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
        state = pagerState /* TO DO height programmcally*/,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        if (page == 0) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                plot?.plotText?.let {
                    Text(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(10.dp),
                        text = it,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                list?.let {
                    items(list) { post ->
                        PostItem(post = post, onToggleFavorite = {
//                           coroutineScope.launch {
                                viewModel.onEvent(VideoViewEvent.DeleteMovieClip(post))
//                            }
                        })
                    }
                }
            }
        }
    }
}
