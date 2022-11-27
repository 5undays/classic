package com.cinema.classic.ui.article

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cinema.classic.PagerView
import com.cinema.classic.YoutubePlayerView
import com.cinema.classic.data.movie.impl.movie1
import com.cinema.classic.model.NaverMovie
import com.cinema.classic.ui.theme.ClassicTheme

private val defaultSpacerSize = 16.dp

@Composable
fun PostContent(
    video_id: String,
    post: NaverMovie
) {
    LazyColumn {
        postContentItems(video_id, post)
    }
}

fun LazyListScope.postContentItems(video_id: String, post: NaverMovie) {
    item {
        //PostHeaderImage(post)
        if (LocalInspectionMode.current) {

        } else {
            YoutubePlayerView(video_id)
        }
        Spacer(Modifier.height(defaultSpacerSize))
        Text(
            post.title.replace("<b>", "").replace("</b>", "") + ", " + post.pubDate,
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(Modifier.height(8.dp))
        if (post.director != "") {
            val directors = post.director.replace("|", ",").substring(0, post.director.length - 1)
            Text(directors, style = MaterialTheme.typography.bodyMedium)
        }
        if (post.actor != "") {
            val actors = post.actor.replace("|", ",").substring(0, post.actor.length - 1)
            Text(actors, style = MaterialTheme.typography.bodyMedium)
        }
        Spacer(Modifier.height(defaultSpacerSize))
        PagerView()
    }
}

@Composable
private fun CodeBlockParagraph(
    text: AnnotatedString,
    textStyle: TextStyle,
    paragraphStyle: ParagraphStyle
) {
    Surface(
        color = MaterialTheme.colorScheme.codeBlockBackground,
        shape = MaterialTheme.shapes.small,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = text,
            style = textStyle.merge(paragraphStyle)
        )
    }
}

private val ColorScheme.codeBlockBackground: Color
    get() = onSurface.copy(alpha = .15f)

@Preview("Post content")
@Preview("Post content (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewPost() {
    ClassicTheme {
        Surface {
            PostContent("mIHr96I8mbk", post = movie1.items[0])
        }
    }
}
