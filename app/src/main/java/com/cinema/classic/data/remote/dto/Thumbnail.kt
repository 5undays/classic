package com.cinema.classic.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Thumbnail(
    @field: SerializedName("url") val url: String,
    @field:SerializedName("width") val width: Int,
    @field:SerializedName("height") val height: Int
)
