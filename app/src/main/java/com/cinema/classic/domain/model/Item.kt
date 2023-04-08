package com.cinema.classic.domain.model

import com.cinema.classic.data.remote.dto.Id
import com.cinema.classic.data.remote.dto.Snippet

data class Item(
    val kind: String, val etag: String, val id: Id, val snippet: Snippet
)