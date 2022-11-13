package com.cinema.classic.ui.article

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cinema.classic.R
import com.cinema.classic.YoutubePlayerView
import com.cinema.classic.data.movie.impl.movie1
import com.cinema.classic.model.Movie
import com.cinema.classic.model.NaverMovie
import com.cinema.classic.model.NaverResult
import com.cinema.classic.ui.theme.ClassicTheme

private val defaultSpacerSize = 16.dp

@Composable
fun PostContent(
    video_id: String,
    post: NaverMovie,
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState()
) {
    LazyColumn(
        contentPadding = PaddingValues(defaultSpacerSize),
        modifier = modifier,
        state = state,
    ) {
        postContentItems(video_id, post)
    }
}

fun LazyListScope.postContentItems(video_id:String, post: NaverMovie) {
    item {
        //PostHeaderImage(post)
        if (LocalInspectionMode.current) {

        } else {
            YoutubePlayerView(video_id)
        }
        Spacer(Modifier.height(defaultSpacerSize))
        Text(post.title.replace("<b>","").replace("</b>", "") + ", " + post.pubDate, style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.height(8.dp))
        val directors = post.director.replace("|", ",").substring(0, post.director.length - 1)
        Text(directors, style = MaterialTheme.typography.bodyMedium)
        val actors = post.actor.replace("|", ",").substring(0, post.actor.length - 1)
        Text(actors, style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.height(defaultSpacerSize))
    }
//    item { PostMetadata(post.metadata, Modifier.padding(bottom = 24.dp)) }
//    items(post.paragraphs) { Paragraph(paragraph = it) }
}

//@Composable
//private fun PostHeaderImage(post: Movie) {
//    val imageModifier = Modifier
//        .heightIn(min = 180.dp)
//        .fillMaxWidth()
//        .clip(shape = MaterialTheme.shapes.medium)
//    Image(
//        painter = painterResource(post.imageId),
//        contentDescription = null, // decorative
//        modifier = imageModifier,
//        contentScale = ContentScale.Crop
//    )
//}

//@Composable
//private fun PostMetadata(
//    metadata: Metadata,
//    modifier: Modifier = Modifier
//) {
//    Row(
//        // Merge semantics so accessibility services consider this row a single element
//        modifier = modifier.semantics(mergeDescendants = true) {}
//    ) {
//        Image(
//            imageVector = Icons.Filled.AccountCircle,
//            contentDescription = null, // decorative
//            modifier = Modifier.size(40.dp),
//            colorFilter = ColorFilter.tint(LocalContentColor.current),
//            contentScale = ContentScale.Fit
//        )
//        Spacer(Modifier.width(8.dp))
//        Column {
//            Text(
//                text = metadata.author.name,
//                style = MaterialTheme.typography.labelLarge,
//                modifier = Modifier.padding(top = 4.dp)
//            )
//
//            Text(
//                text = stringResource(
//                    id = R.string.article_post_min_read,
//                    formatArgs = arrayOf(
//                        metadata.date,
//                        metadata.readTimeMinutes
//                    )
//                ),
//                style = MaterialTheme.typography.bodySmall
//            )
//        }
//    }
//}

//@Composable
//private fun Paragraph(paragraph: Paragraph) {
//    val (textStyle, paragraphStyle, trailingPadding) = paragraph.type.getTextAndParagraphStyle()
//
//    val annotatedString = paragraphToAnnotatedString(
//        paragraph,
//        MaterialTheme.typography,
//        MaterialTheme.colorScheme.codeBlockBackground
//    )
//    Box(modifier = Modifier.padding(bottom = trailingPadding)) {
//        when (paragraph.type) {
//            ParagraphType.Bullet -> BulletParagraph(
//                text = annotatedString,
//                textStyle = textStyle,
//                paragraphStyle = paragraphStyle
//            )
//            ParagraphType.CodeBlock -> CodeBlockParagraph(
//                text = annotatedString,
//                textStyle = textStyle,
//                paragraphStyle = paragraphStyle
//            )
//            ParagraphType.Header -> {
//                Text(
//                    modifier = Modifier.padding(4.dp),
//                    text = annotatedString,
//                    style = textStyle.merge(paragraphStyle)
//                )
//            }
//            else -> Text(
//                modifier = Modifier.padding(4.dp),
//                text = annotatedString,
//                style = textStyle
//            )
//        }
//    }
//}

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

@Composable
private fun BulletParagraph(
    text: AnnotatedString,
    textStyle: TextStyle,
    paragraphStyle: ParagraphStyle
) {
    Row {
        with(LocalDensity.current) {
            // this box is acting as a character, so it's sized with font scaling (sp)
            Box(
                modifier = Modifier
                    .size(8.sp.toDp(), 8.sp.toDp())
                    .alignBy {
                        // Add an alignment "baseline" 1sp below the bottom of the circle
                        9.sp.roundToPx()
                    }
                    .background(LocalContentColor.current, CircleShape),
            ) { /* no content */ }
        }
        Text(
            modifier = Modifier
                .weight(1f)
                .alignBy(FirstBaseline),
            text = text,
            style = textStyle.merge(paragraphStyle)
        )
    }
}

private data class ParagraphStyling(
    val textStyle: TextStyle,
    val paragraphStyle: ParagraphStyle,
    val trailingPadding: Dp
)

//@Composable
//private fun ParagraphType.getTextAndParagraphStyle(): ParagraphStyling {
//    val typography = MaterialTheme.typography
//    var textStyle: TextStyle = typography.bodyLarge
//    var paragraphStyle = ParagraphStyle()
//    var trailingPadding = 24.dp
//
//    when (this) {
//        ParagraphType.Caption -> textStyle = typography.labelMedium
//        ParagraphType.Title -> textStyle = typography.headlineLarge
//        ParagraphType.Subhead -> {
//            textStyle = typography.headlineSmall
//            trailingPadding = 16.dp
//        }
//        ParagraphType.Text -> {
//            textStyle = typography.bodyLarge.copy(lineHeight = 28.sp)
//        }
//        ParagraphType.Header -> {
//            textStyle = typography.headlineMedium
//            trailingPadding = 16.dp
//        }
//        ParagraphType.CodeBlock -> textStyle = typography.bodyLarge.copy(
//            fontFamily = FontFamily.Monospace
//        )
//        ParagraphType.Quote -> textStyle = typography.bodyLarge
//        ParagraphType.Bullet -> {
//            paragraphStyle = ParagraphStyle(textIndent = TextIndent(firstLine = 8.sp))
//        }
//    }
//    return ParagraphStyling(
//        textStyle,
//        paragraphStyle,
//        trailingPadding
//    )
//}

//private fun paragraphToAnnotatedString(
//    paragraph: Paragraph,
//    typography: Typography,
//    codeBlockBackground: Color
//): AnnotatedString {
//    val styles: List<AnnotatedString.Range<SpanStyle>> = paragraph.markups
//        .map { it.toAnnotatedStringItem(typography, codeBlockBackground) }
//    return AnnotatedString(text = paragraph.text, spanStyles = styles)
//}

//fun Markup.toAnnotatedStringItem(
//    typography: Typography,
//    codeBlockBackground: Color
//): AnnotatedString.Range<SpanStyle> {
//    return when (this.type) {
//        MarkupType.Italic -> {
//            AnnotatedString.Range(
//                typography.bodyLarge.copy(fontStyle = FontStyle.Italic).toSpanStyle(),
//                start,
//                end
//            )
//        }
//        MarkupType.Link -> {
//            AnnotatedString.Range(
//                typography.bodyLarge.copy(textDecoration = TextDecoration.Underline).toSpanStyle(),
//                start,
//                end
//            )
//        }
//        MarkupType.Bold -> {
//            AnnotatedString.Range(
//                typography.bodyLarge.copy(fontWeight = FontWeight.Bold).toSpanStyle(),
//                start,
//                end
//            )
//        }
//        MarkupType.Code -> {
//            AnnotatedString.Range(
//                typography.bodyLarge
//                    .copy(
//                        background = codeBlockBackground,
//                        fontFamily = FontFamily.Monospace
//                    ).toSpanStyle(),
//                start,
//                end
//            )
//        }
//    }
//}

private val ColorScheme.codeBlockBackground: Color
    get() = onSurface.copy(alpha = .15f)

@Preview("Post content")
@Preview("Post content (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewPost() {
    ClassicTheme {
        Surface {
            PostContent("mIHr96I8mbk",post = movie1.items[0])
        }
    }
}
