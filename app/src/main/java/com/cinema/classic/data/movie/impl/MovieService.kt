package com.cinema.classic.data.movie.impl

import androidx.compose.runtime.MutableState
import com.cinema.classic.Retrofit
import com.cinema.classic.data.movie.MovieRepository
import com.cinema.classic.model.Item
import com.cinema.classic.model.NaverMovie
import com.cinema.classic.model.NaverResult
import com.cinema.classic.model.Youtube
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieService {
    fun getMovieData(url: String, title: String, result: MutableState<NaverMovie>){
        Retrofit.api.get2(url, title)
            .enqueue(object : Callback<NaverResult> {
                override fun onResponse(call: Call<NaverResult>, response: Response<NaverResult>) {
                   response.body()?.items?.get(0).let { r -> result }
                }

                override fun onFailure(call: Call<NaverResult>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

    fun initializeMovieData(url: String, result: MutableList<Item>) {
        Retrofit.api.get(url).enqueue(object : Callback<Youtube> {
            override fun onResponse(call: Call<Youtube>, response: Response<Youtube>) {
                var list: List<Item>? = response.body()?.items
                if (list != null) {
                    for (i in 0 until list.size) {
                        // on below line we are adding data to course list.
                        list.get(i).let { result.add(it) }
                    }
                }
            }

            override fun onFailure(call: Call<Youtube>, t: Throwable) {
            }
        })
    }

}