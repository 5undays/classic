package com.cinema.classic.data.remote.dto

import com.cinema.classic.domain.model.Item
import com.google.gson.annotations.SerializedName

data class ItemDto(
    @field:SerializedName("kind") val kind: String,
    @field:SerializedName("etag") val etag: String,
    @field:SerializedName("id") val id: Id,
    @field:SerializedName("snippet") val snippet: Snippet
)

fun ItemDto.toItem(): Item {
    return Item(
        kind = kind,
        etag = etag,
        id = id,
        snippet = snippet
    )
}