package com.cinema.classic.data.movie

import com.cinema.classic.BuildConfig
import com.cinema.classic.model.NaverResult
import com.cinema.classic.model.Youtube
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Url

interface MovieRepository {
    @Headers(
        "X-Naver-Client-Id: " + BuildConfig.NAVER_CLIENT_KEY,
        "X-Naver-Client-Secret: Y19lKc_Nv9"
    )
    @GET
    open fun get2(@Url url: String, @Query("query") post: String?): Call<NaverResult>

    @GET
    open fun get(
        @Url url: String,
        @Query("part") part: String = "snippet",
        @Query("channelId") channelId: String = "UCvH6u_Qzn5RQdz9W198umDw",
        @Query("order") order: String = "date",
        @Query("key") key: String = BuildConfig.GOOGLE_API_KEY
    ): Call<Youtube>
}