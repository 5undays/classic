package com.cinema.classic.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface YoutubeDao {
    @Upsert
    suspend fun upsertAll(youtubeList: List<YoutubeEntity>)

    @Query("SELECT * FROM youtube_entity")
    fun pagingSource(): PagingSource<Int, YoutubeEntity>

    @Query("DELETE FROM youtube_entity")
    suspend fun clearAll()
}