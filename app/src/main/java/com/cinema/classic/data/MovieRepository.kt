package com.cinema.classic.data

import com.cinema.classic.api.MovieService
import com.cinema.classic.model.KmdbResult
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val service: MovieService
) {
    suspend fun load(): Response<Youtube> {
        return service.get("https://www.googleapis.com/youtube/v3/search")
    }

    suspend fun get(title: String, year: Int): Response<NaverResult> {
        return service.get2("https://openapi.naver.com/v1/search/movie.json", title, year, year)
    }

    suspend fun get2(title: String): Response<KmdbResult> {
        return service.get3(
            "https://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp", title
        )
    }
}