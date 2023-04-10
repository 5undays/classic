package com.cinema.classic.di

import com.cinema.classic.common.Constants
import com.cinema.classic.data.remote.MovieApi
import com.cinema.classic.data.repository.MovieRepositoryImpl
import com.cinema.classic.domain.repository.MovieRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun movieService(): MovieApi {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
            .create(MovieApi::class.java)
    }


}