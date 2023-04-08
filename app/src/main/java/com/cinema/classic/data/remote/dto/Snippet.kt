package com.cinema.classic.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Snippet(
    @field:SerializedName("publishedAt") val publishedAt: String,
    @field:SerializedName("channelId") val channelId: String,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("description") val description: String,
    @field:SerializedName("thumbnails") val thumbnails: Thumbnails,
    @field:SerializedName("channelTitle") val channelTitle: String,
    @field:SerializedName("liveBroadcastContent") val liveBroadcastContent: String,
    @field:SerializedName("publishTime") val publishTime: String
)

