package com.cinema.classic.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PageInfo(
    @field:SerializedName("totalResult") val totalResult: Int,
    @field:SerializedName("resultsPerPage") val resultsPerPage: Int
)
