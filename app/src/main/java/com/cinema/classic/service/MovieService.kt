package com.cinema.classic.service

import android.provider.Settings.System.getString
import com.cinema.classic.R
import com.cinema.classic.model.NaverResult
import com.cinema.classic.model.Youtube
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Url

interface MovieService {
    @Headers(
        "X-Naver-Client-Id: s6M3ZVY1UcNhy5FDF0KC",
        "X-Naver-Client-Secret: Y19lKc_Nv9"
    )
    @GET
    open fun get(@Url url: String, @Query("query") post: String?): Call<NaverResult>

    @GET
    open fun get(
        @Url url: String,
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("order") order: String,
        @Query("key") key: String = "AIzaSyDcBkLYiDrMo2RsNsCds4BKKpoJbfrthrQ"
    ): Call<Youtube>
}