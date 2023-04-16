package com.cinema.classic.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.cinema.classic.common.Constants
import com.cinema.classic.data.local.MovieClipDatabase
import com.cinema.classic.data.local.YoutubeEntity
import com.cinema.classic.data.local.toItem
import com.cinema.classic.data.remote.MovieApi
import okio.IOException
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class YoutubeRemoteMediator(private val database: MovieClipDatabase, private val api: MovieApi) :
    RemoteMediator<Int, YoutubeEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, YoutubeEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    lastItem.videoId
                }
            }

            val youtube = api.getYoutubeVideoList(
                url = Constants.BASE_URL, pageToken = loadKey
            )
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.youtubeDao.clearAll()
                }
                val movie = youtube.items.map { it.toItem() }
                database.youtubeDao.upsertAll(movie)
            }
            return MediatorResult.Success(endOfPaginationReached = youtube.items.isEmpty())
        } catch(e : IOException) {
            MediatorResult.Error(e)
        } catch (e : HttpException) {
            MediatorResult.Error(e)
        }
    }

}