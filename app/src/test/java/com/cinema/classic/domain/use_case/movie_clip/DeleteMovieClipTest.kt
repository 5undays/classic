package com.cinema.classic.domain.use_case.movie_clip

import com.cinema.classic.data.repository.MovieClipRepositoryImplTest
import com.cinema.classic.domain.model.MovieClip
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.*

class DeleteMovieClipTest {
    private lateinit var deleteMovieClip: DeleteMovieClip
    private lateinit var movieClipRepositoryImplTest: MovieClipRepositoryImplTest

    val movieClip = MovieClip(
        movie_year = 1991,
        movie_name = "하녀",
        time = 1000911F,
        reg_date = Calendar.getInstance(),
        video_id = "1234"
    )

    @Before
    fun setUp() {
        movieClipRepositoryImplTest = MovieClipRepositoryImplTest()
        deleteMovieClip = DeleteMovieClip(movieClipRepositoryImplTest)

        runBlocking {
            movieClipRepositoryImplTest.insertMovieClip(movieClip)
        }
    }

    @Test
    fun delete() {
        runBlocking {
            deleteMovieClip(movieClip)
        }

        assertThat(MovieClipRepositoryImplTest().getMovieClips(movieClip.video_id)).isNotEqualTo(
            movieClip
        )
    }
}