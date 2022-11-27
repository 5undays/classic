package com.cinema.classic.ui.article

import android.content.Context
import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cinema.classic.R
import com.cinema.classic.data.movie.impl.movie1
import com.cinema.classic.model.NaverMovie
import com.cinema.classic.ui.theme.ClassicTheme
import com.cinema.classic.ui.utils.BookmarkButton
import com.cinema.classic.ui.utils.FavoriteButton
import com.cinema.classic.ui.utils.ShareButton
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleScreen(
    video_id:String,
    post: NaverMovie,
    onPostChange: (NaverMovie) -> Unit,
    isExpandedScreen: Boolean,
    onBack: () -> Unit,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState()
) {
    var showUnimplementedActionDialog by rememberSaveable { mutableStateOf(false) }
    if (showUnimplementedActionDialog) {
        FunctionalityNotAvailablePopup { showUnimplementedActionDialog = false }
    }
    val onDismissState by rememberUpdatedState(onPostChange)
    onDismissState(post)
    Row(modifier.fillMaxSize()) {
        val context = LocalContext.current
        ArticleScreenContent(
            video_id = video_id,
            movie = post,
            // Allow opening the Drawer if the screen is not expanded
            navigationIconContent = {
                if (!isExpandedScreen) {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.cd_navigate_up),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            },
            // Show the bottom bar if the screen is not expanded
            bottomBarContent = {
                if (!isExpandedScreen) {
                    BottomAppBar(
                        actions = {
                            FavoriteButton(onClick = { showUnimplementedActionDialog = true })
                            BookmarkButton(isBookmarked = isFavorite, onClick = onToggleFavorite)
                            ShareButton(onClick = { sharePost(post, context)  })
                            //TextSettingsButton(onClick = { showUnimplementedActionDialog = true })
                        }
                    )
                }
            },
            lazyListState = lazyListState
        )
    }
}

/**
 * Stateless Article Screen that displays a single post.
 *
 * @param movie (state) item to display
 * @param navigationIconContent (UI) content to show for the navigation icon
 * @param bottomBarContent (UI) content to show for the bottom bar
 */
@ExperimentalMaterial3Api
@Composable
private fun ArticleScreenContent(
    video_id:String,
    movie: NaverMovie,
    navigationIconContent: @Composable () -> Unit = { },
    bottomBarContent: @Composable () -> Unit = { },
    lazyListState: LazyListState = rememberLazyListState()
) {
//    val topAppBarState = rememberTopAppBarState()
//    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = movie.director.orEmpty(),
//                navigationIconContent = navigationIconContent,
//                scrollBehavior = scrollBehavior
//            )
//        },
//        bottomBar = bottomBarContent
//    ) { innerPadding ->
//            PostContent(
//                video_id = video_id,
//                post = movie
//            )
//    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(
    title: String,
    navigationIconContent: @Composable () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior?,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Row {
                Text(
                    text = stringResource(R.string.app_name, title),
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        },
        navigationIcon = navigationIconContent,
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}

/**
 * Display a popup explaining functionality not available.
 *
 * @param onDismiss (event) request the popup be dismissed
 */
@Composable
private fun FunctionalityNotAvailablePopup(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        text = {
            Text(
                text = stringResource(id = R.string.article_functionality_not_available),
                style = MaterialTheme.typography.bodyLarge
            )
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(id = R.string.close))
            }
        }
    )
}

/**
 * Show a share sheet for a post
 *
 * @param post to share
 * @param context Android context to show the share sheet in
 */
fun sharePost(post: NaverMovie, context: Context) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TITLE, post.title)
        putExtra(Intent.EXTRA_TEXT, post.link)
    }
    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.article_share_post)
        )
    )
}