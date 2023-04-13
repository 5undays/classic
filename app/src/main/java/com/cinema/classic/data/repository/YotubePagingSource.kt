package com.cinema.classic.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cinema.classic.common.Constants
import com.cinema.classic.data.remote.MovieApi
import com.cinema.classic.data.remote.dto.toItem
import com.cinema.classic.domain.model.Movie
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class YotubePagingSource @Inject constructor(private val api: MovieApi) :
    PagingSource<String, Movie>() {
    override suspend fun load(params: LoadParams<String>): LoadResult<String, Movie> {
        return try {
            val result =
                api.getYoutubeVideoList(
                    url = Constants.BASE_URL, pageToken = params.key
                )
            return LoadResult.Page(
                data = result.items.map { it.toItem() },
                prevKey = result.prevPageToken,
                nextKey = result.nextPageToken
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<String, Movie>): String? {
        TODO("Not yet implemented")
    }
}