package com.cinema.classic.domain.use_case.movie_clip

import com.cinema.classic.data.repository.MovieClipRepositoryImplTest
import com.cinema.classic.domain.model.MovieClip
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.*

class AddMovieClipTest {
    private lateinit var addMovieClips: AddMovieClip
    private lateinit var movieClipRepositoryImplTest: MovieClipRepositoryImplTest

    @Before
    fun setUp() {
        movieClipRepositoryImplTest = MovieClipRepositoryImplTest()
        addMovieClips = AddMovieClip(movieClipRepositoryImplTest)

    }

    @Test
    fun insert() = runBlocking {
        addMovieClips(
            MovieClip(
                movie_year = 1991,
                movie_name = "하녀",
                time = 1000911F,
                reg_date = Calendar.getInstance(),
                video_id = "1234"
            )
        )

        val result = movieClipRepositoryImplTest.getMovieClips("하녀").first()
        assertThat(result[0].movie_year).isEqualTo(1991)
    }

}