package com.cinema.classic.di

import com.cinema.classic.data.local.MovieClipDatabase
import com.cinema.classic.data.remote.MovieApi
import com.cinema.classic.data.repository.MovieClipRepositoryImpl
import com.cinema.classic.data.repository.MovieRepositoryImpl
import com.cinema.classic.domain.repository.MovieClipRepository
import com.cinema.classic.domain.repository.MovieRepository
import com.cinema.classic.domain.use_case.VideoViewUseCase
import com.cinema.classic.domain.use_case.kmdb.GetKmdbUserCase
import com.cinema.classic.domain.use_case.movie_clip.AddMovieClip
import com.cinema.classic.domain.use_case.movie_clip.DeleteMovieClip
import com.cinema.classic.domain.use_case.movie_clip.GetMovieClips
import com.cinema.classic.domain.use_case.naver.GetNaverUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideMovieClipRepository(db: MovieClipDatabase): MovieClipRepository {
        return MovieClipRepositoryImpl(db.movieClipDao)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieApi): MovieRepository {
        return MovieRepositoryImpl(api)
    }

//    fun provideMovieUseCase(
//        movieClipRepository: MovieClipRepository,
//        movieRepository: MovieRepository
//    ): MainUseCase {
//        return MainUseCase(
//            getNaverUseCase = GetNaverUseCase(movieRepository),
//            getLastMovieClip = GetLastMovieClip(movieClipRepository),
//            getYoutubeListUseCase = GetYoutubeListUseCase(movieRepository)
//        )
//    }

    @Provides
    @Singleton
    fun provideVideoViewUseCase(
        movieRepository: MovieRepository,
        movieClipRepository: MovieClipRepository
    ): VideoViewUseCase {
        return VideoViewUseCase(
            getNaverUseCase = GetNaverUseCase(movieRepository),
            getKmdbUserCase = GetKmdbUserCase(movieRepository),
            getMovieClips = GetMovieClips(movieClipRepository),
            addMovieClip = AddMovieClip(movieClipRepository),
            deleteMovieClip = DeleteMovieClip(movieClipRepository)
        )
    }
}