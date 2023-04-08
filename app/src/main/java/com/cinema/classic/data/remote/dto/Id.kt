package com.cinema.classic.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Id(
    @field: SerializedName("kind") val kind: String,
    @field:SerializedName("videoId") val videoId: String
)