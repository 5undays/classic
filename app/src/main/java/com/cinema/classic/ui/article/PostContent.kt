package com.cinema.classic.ui.article

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.cinema.classic.data.interest.MovieClip
import com.cinema.classic.databinding.FragmentContainerYoutubeBinding
import com.cinema.classic.fragment.YoutubeFragment
import com.cinema.classic.ui.theme.ClassicTheme
import com.cinema.classic.ui.theme.ClassicTheme2
import com.cinema.classic.ui.utils.BookmarkButton
import com.cinema.classic.viewmodels.VideoViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


private val defaultSpacerSize = 16.dp

@Composable
fun movieData(video_id: String, viewModel: VideoViewModel) {
    val data1 by viewModel.data.observeAsState()
    val data2 by viewModel.movieClipList.observeAsState()

    ClassicTheme2 {
        data1?.let {
            LazyColumn {
                item {
                    if (!LocalInspectionMode.current) {
                        if (data2?.size == 0) {
                            YoutubePlayerView(video_id, 0f)
                        } else {
                            YoutubePlayerView(video_id, data2?.get(0)?.time!!)
                        }
                    }
                    Spacer(Modifier.height(defaultSpacerSize))
                    Text(
                        it.title.replace("<b>", "").replace("</b>", "") + ", " + it.pubDate,
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                    Spacer(Modifier.height(8.dp))
                    if (it.director != "") {
                        val directors =
                            it.director.replace("|", ",").substring(0, it.director.length - 1)
                        Text(
                            directors,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                    if (it.actor != "") {
                        val actors = it.actor.replace("|", ",").substring(0, it.actor.length - 1)
                        Text(
                            actors,
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                    Spacer(Modifier.height(defaultSpacerSize))
                    tabLayout(viewModel)
                }
            }
        }
    }
}

@Composable
fun YoutubePlayerView(video_id: String, time: Float = 0f) {
    AndroidViewBinding(FragmentContainerYoutubeBinding::inflate) {
        val f = fragmentContainerView.getFragment<YoutubeFragment>()
        f.initialVideo(video_id, time)
    }
}

val pages = listOf("PLOT", "BOOKMARK")

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun tabLayout(viewModel: VideoViewModel) {
    var list = viewModel.movieClipList.observeAsState()
    val plot by viewModel.plotData.observeAsState()

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
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
                    modifier = Modifier.fillMaxHeight()
                )
            }
        }
        HorizontalPager(
            count = pages.size,
            state = pagerState /* TO DO height programmcally*/,
            modifier = Modifier.height(300.dp)
        ) { page ->
            if (page == 0) {
                Column(modifier = Modifier
                    .fillMaxHeight()
                    .verticalScroll(scrollState)) {
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
                    list.value?.let {
                        items(list.value!!) { post ->
                            PostItem(post = post, onToggleFavorite = {
                                coroutineScope.launch {
                                    viewModel.removeMovieClip(post)
                                }
                            })
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
fun PostItem(
    post: MovieClip,
    onToggleFavorite: () -> Unit,
    modifier: Modifier = Modifier
) {
    val sdf = SimpleDateFormat("HH:mm:ss")
    sdf.timeZone = TimeZone.getTimeZone("UTC")

    Card(
        modifier = modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .height(60.dp)
    ) {
        Row(
            modifier = modifier
                .padding(vertical = 8.dp, horizontal = 8.dp)
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = sdf.format(post.time * 1000),
                modifier = Modifier.weight(0.5f)
            )
            Text(
                text = SimpleDateFormat(
                    "yyyy-MM-dd",
                    Locale.getDefault()
                ).format(post.reg_date.timeInMillis),
                color = Color.Black,
                modifier = Modifier.wrapContentSize()
            )
            BookmarkButton(onClick = onToggleFavorite)
        }
    }
}

@Preview("Post content")
@Preview("Post content (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewPost() {
    ClassicTheme {
        Surface {
            PostItem(post = MovieClip("2j7uys48wwc", 2266.77F, Calendar.getInstance()), {})
        }
    }
}
