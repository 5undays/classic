package com.cinema.classic.data.remote

import com.cinema.classic.BuildConfig
import com.cinema.classic.data.remote.dto.*
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Url


interface MovieApi {
    @GET
    suspend fun getMovieByKmdb(
        @Url url: String,
        @Query("title") title: String,
        @Query("ServiceKey") serviceKey: String = "05W1A4LLI30JJBDZC626",
        @Query("collection") collection: String = "kmdb_new2"
    ): KmdbDto

    @Headers(
        "X-Naver-Client-Id: s6M3ZVY1UcNhy5FDF0KC",
        "X-Naver-Client-Secret: Y19lKc_Nv9"
    )
    @GET
    suspend fun getMovieByNaver(
        @Url url: String,
        @Query("query") title: String,
        @Query("yearto") yearto: Int,
        @Query("yearfrom") yearfrom: Int
    ): NaverDto

    @GET
    suspend fun getYoutubeVideoList(
        @Url url: String,
        @Query("part") part: String = "snippet",
        @Query("channelId") channelId: String = "UCvH6u_Qzn5RQdz9W198umDw",
        @Query("order") order: String = "date",
        @Query("safeSearch") safeSearch: String = "strict",
        @Query("key") key: String = BuildConfig.GOOGLE_API_KEY
    ): Youtube

}