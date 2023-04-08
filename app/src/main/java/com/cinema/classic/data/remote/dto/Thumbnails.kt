package com.cinema.classic.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Thumbnails(
    @field:SerializedName("default") val default: Thumbnail,
    @field:SerializedName("medium") val medium: Thumbnail,
    @field:SerializedName("high") val high: Thumbnail
)
