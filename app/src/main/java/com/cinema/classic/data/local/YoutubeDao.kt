package com.cinema.classic.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface YoutubeDao {
    @Upsert
    suspend fun upsertAll(youtubeList: List<YoutubeEntity>)

    @Query("SELECT * FROM youtubeentity")
    fun pagingSource(): PagingSource<Int, YoutubeEntity>

    @Query("DELETE FROM youtubeentity")
    suspend fun clearAll()
}