package com.cinema.classic.presentation.main_list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cinema.classic.data.remote.dto.toItem
import com.cinema.classic.domain.model.Movie
import com.cinema.classic.domain.use_case.youtube.GetYoutubeListUseCase
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class YotubePagingSource @Inject constructor(
    private val getYoutubeListUseCase: GetYoutubeListUseCase
) : PagingSource<String, Movie>() {
    override suspend fun load(params: LoadParams<String>): LoadResult<String, Movie> {
        return try {
            val result = getYoutubeListUseCase(pageToken = params.key)
            return LoadResult.Page(
                data = result.items.map { it.toItem() },
                prevKey = result.prevPageToken,
                nextKey = result.nextPageToken
            )
        } catch (e: IOException) {
            println(e)
            LoadResult.Error(e)
        } catch (e: HttpException) {
            println(e)
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<String, Movie>): String? {
        TODO("Not yet implemented")
    }
}