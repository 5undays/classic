package com.cinema.classic.service

import com.cinema.classic.model.NaverResult
import com.cinema.classic.model.Youtube
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieService {
    @Headers(
        "X-Naver-Client-Id: s6M3ZVY1UcNhy5FDF0KC",
        "X-Naver-Client-Secret: Y19lKc_Nv9"
    )
    @GET("movie.json")
    open fun get(@Query("query") post: String?): Call<NaverResult>

    @GET("search")
    open fun get(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("order") order: String,
        @Query("key") key:String = "AIzaSyDcBkLYiDrMo2RsNsCds4BKKpoJbfrthrQ"
    ): Call<Youtube>
}