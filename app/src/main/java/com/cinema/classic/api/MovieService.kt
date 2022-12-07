package com.cinema.classic.api

import com.cinema.classic.BuildConfig
import com.cinema.classic.model.KmdbResult
import com.cinema.classic.model.NaverResult
import com.cinema.classic.model.Youtube
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Url


interface MovieService {
    @GET
    suspend fun get3(
        @Url url: String,
        @Query("title") title: String,
        @Query("ServiceKey") serviceKey: String = "05W1A4LLI30JJBDZC626",
        @Query("collection") collection: String = "kmdb_new2"
    ): Response<KmdbResult>

    @Headers(
        "X-Naver-Client-Id: " + BuildConfig.NAVER_CLIENT_KEY,
        "X-Naver-Client-Secret: Y19lKc_Nv9"
    )
    @GET
    suspend fun get2(
        @Url url: String,
        @Query("query") title: String,
        @Query("yearto") yearto: Int,
        @Query("yearfrom") yearfrom: Int
    ): Response<NaverResult>

    @GET
    suspend fun get(
        @Url url: String,
        @Query("part") part: String = "snippet",
        @Query("channelId") channelId: String = "UCvH6u_Qzn5RQdz9W198umDw",
        @Query("order") order: String = "date",
        @Query("safeSearch") safeSearch: String = "strict",
        @Query("key") key: String = BuildConfig.GOOGLE_API_KEY
    ): Response<Youtube>

    companion object {
        private const val BASE_URL = "https://www.googleapis.com/youtube/v3/"

        fun create(): MovieService {
            val gson = GsonBuilder()
                .setLenient()
                .create()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(MovieService::class.java)
        }
    }
}