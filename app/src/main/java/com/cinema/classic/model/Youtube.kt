package com.cinema.classic.model

data class Youtube(
    val kind: String,
    val etag: String,
    val nextPageToken: String,
    val regionCode: String,
    val pageInfo: PageInfo,
    val items: List<Item>
)

data class Item(
    val kind: String,
    val etag:String,
    val id: Id,
    val snippet:Snippet
)

data class Snippet (
    val publishedAt:String,
    val channelId:String,
    val title:String,
    val description:String,
    val thumbnails:Thumbnails,
    val channelTitle:String,
    val liveBroadcastContent:String,
    val publishTime:String
)

data class Thumbnails (
    val default:Thumbnail,
    val medium:Thumbnail,
    val high:Thumbnail
)

data class Thumbnail(
    val url:String,
    val width:Int,
    val height:Int
)

data class Id (
    val kind:String,
    val videoId:String
)

data class PageInfo(
    val totalResult: Int,
    val resultsPerPage: Int
)

object YoutubeRepo {
    //fun getPosts(): List<Post> = sample
    fun getFeaturedPost(): Item = sample
}

private val sample = Item(
    kind = "youtube#searchResult",
    etag = "rbrkxpxGxA745cw2mj7eqMWjwL8",
    id = Id(
        kind = "youtube#video",
        videoId = "2j7uys48wwc"
    ),
    snippet = Snippet(
        publishedAt = "2022-07-24T15:00:11Z",
        channelId = "UCvH6u_Qzn5RQdz9W198umDw",
        title = "꿈(1955) / Dream (Kkum) (1955)",
        description = "영화보다 영화 같은 삶: 영화감독 신상옥 — Google Arts & Culture https://artsandculture.google.com/story/HwUxg6trdAUA8A 영화의 ..",
        thumbnails = Thumbnails(
            default = Thumbnail(
                url = "https://i.ytimg.com/vi/2j7uys48wwc/default.jpg",
                width = 120,
                height = 90
            ),
            medium = Thumbnail(
                url = "https://i.ytimg.com/vi/2j7uys48wwc/mqdefault.jpg",
                width = 320,
                height = 180
            ),
            high = Thumbnail(
                url = "https://i.ytimg.com/vi/2j7uys48wwc/hqdefault.jpg",
                width = 480,
                height = 360
            )
        ),
        channelTitle = "한국고전영화 Korean Classic Film",
        liveBroadcastContent = "none",
        publishTime = "2022-07-24T15:00:11Z"
    )
)