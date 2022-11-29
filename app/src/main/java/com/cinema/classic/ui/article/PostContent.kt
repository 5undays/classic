package com.cinema.classic.ui.article

import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import coil.compose.AsyncImage
import com.cinema.classic.YoutubeActivity
import com.cinema.classic.data.interest.MovieClip
import com.cinema.classic.data.movie.impl.movie1
import com.cinema.classic.databinding.FragmentContainerViewpagerBinding
import com.cinema.classic.databinding.FragmentContainerYoutubeBinding
import com.cinema.classic.fragment.ViewpagerFragment
import com.cinema.classic.fragment.YoutubeFragment
import com.cinema.classic.model.Item
import com.cinema.classic.model.NaverMovie
import com.cinema.classic.ui.theme.ClassicTheme
import com.cinema.classic.ui.theme.ClassicTheme2
import com.cinema.classic.viewmodels.VideoViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

private val defaultSpacerSize = 16.dp

@Composable
fun movieData(video_id: String, viewModel: VideoViewModel) {
    val data1 by viewModel.data.observeAsState()
    data1?.let {
        LazyColumn {
            //postContentItems(video_id, it)
            item {
                if (LocalInspectionMode.current) {

                } else {
                    YoutubePlayerView(video_id)
                }
                Spacer(Modifier.height(defaultSpacerSize))
                Text(
                    it.title.replace("<b>", "").replace("</b>", "") + ", " + it.pubDate,
                    style = MaterialTheme.typography.h4
                )
                Spacer(Modifier.height(8.dp))
                if (it.director != "") {
                    val directors = it.director.replace("|", ",").substring(0, it.director.length - 1)
                    Text(directors, style = MaterialTheme.typography.body1)
                }
                if (it.actor != "") {
                    val actors = it.actor.replace("|", ",").substring(0, it.actor.length - 1)
                    Text(actors, style = MaterialTheme.typography.body2)
                }
                Spacer(Modifier.height(defaultSpacerSize))
                //PagerView()
                tabLayout(viewModel)
            }
        }
    }
}

var currentSecond = 0f

@Composable
fun YoutubePlayerView(video_id: String) {
    AndroidViewBinding(FragmentContainerYoutubeBinding::inflate) {
        val f = fragmentContainerView.getFragment<YoutubeFragment>()
        f.initialVideo(video_id)
        //currentSecond = f.currentSecond
    }
}

@Composable
fun PagerView() {
    AndroidViewBinding(FragmentContainerViewpagerBinding::inflate) {
        val f = fragmentContainerViewpager.getFragment<ViewpagerFragment>()
    }
}

@Preview("제목")
@Composable
fun prevGetMovieData() {
    ClassicTheme2 {
        //getMovieData(title = "골목안 풍경 (1962) \n What Happens in a Alley")
    }
}

val pages = listOf("PLOT", "BOOKMARK")

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun tabLayout(viewModel: VideoViewModel) {
    var list = viewModel.movieClipList.observeAsState()
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        val pagerState = rememberPagerState()
        val coroutineScope = rememberCoroutineScope()

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
                            //pagerState.scrollToPage(index)
                        }
                    },
                )
            }

        }
        HorizontalPager(count = pages.size, state = pagerState) { page ->
            if (page == 0) {
                Text(
                    modifier = Modifier.wrapContentSize(),
                    text = "tttt",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp
                )
            } else {
                LazyColumn(modifier = Modifier.height(300.dp)) {
                    list.value?.let {
                        items(list.value!!) { post ->
                            PostItem(post = post)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PostItem(
    post: MovieClip,
    modifier: Modifier = Modifier
) {
    val ctx = LocalContext.current

    Card(
        modifier = modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .height(150.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = modifier.padding(vertical = 8.dp, horizontal = 8.dp)
        ) {
            //Text(text = post.time)
            Text(text = post.video_id, color = Color.White)
        }
    }
}

@Preview("Post content")
@Preview("Post content (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewPost() {
    ClassicTheme {
        Surface {
            //PostContent("mIHr96I8mbk", post = movie1.items[0])
        }
    }
}
