package com.cinema.classic.data

import com.google.gson.annotations.SerializedName

data class Youtube(
    @field:SerializedName("kind") val kind: String,
    @field:SerializedName("etag") val etag: String,
    @field:SerializedName("nextPageToken") val nextPageToken: String,
    @field:SerializedName("regionCode") val regionCode: String,
    @field:SerializedName("pageInfo") val pageInfo: PageInfo,
    @field:SerializedName("items") val items: List<Item>
)

data class Item(
    @field:SerializedName("kind") val kind: String,
    @field:SerializedName("etag") val etag:String,
    @field:SerializedName("id") val id: Id,
    @field:SerializedName("snippet") val snippet: Snippet
)

data class Snippet (
    @field:SerializedName("publishedAt") val publishedAt:String,
    @field:SerializedName("channelId") val channelId:String,
    @field:SerializedName("title") val title:String,
    @field:SerializedName("description") val description:String,
    @field:SerializedName("thumbnails") val thumbnails: Thumbnails,
    @field:SerializedName("channelTitle") val channelTitle:String,
    @field:SerializedName("liveBroadcastContent") val liveBroadcastContent:String,
    @field:SerializedName("publishTime") val publishTime:String
)

data class Thumbnails (
    @field:SerializedName("default") val default: Thumbnail,
    @field:SerializedName("medium") val medium: Thumbnail,
    @field:SerializedName("high") val high: Thumbnail
)

data class Thumbnail(
    @field:SerializedName("url") val url:String,
    @field:SerializedName("width") val width:Int,
    @field:SerializedName("height") val height:Int
)

data class Id (
    @field:SerializedName("kind") val kind:String,
    @field:SerializedName("videoId") val videoId:String
)

data class PageInfo(
    @field:SerializedName("totalResult") val totalResult: Int,
    @field:SerializedName("resultsPerPage") val resultsPerPage: Int
)

object YoutubeRepo {
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