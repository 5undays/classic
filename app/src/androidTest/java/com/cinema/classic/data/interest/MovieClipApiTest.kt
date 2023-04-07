package com.cinema.classic.data.interest

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.cinema.classic.data.local.AppDatabase
import kotlinx.coroutines.runBlocking
import com.cinema.classic.data.local.MovieClipApi
import org.junit.After
import org.junit.Before
import org.junit.Test

class MovieClipApiTest {
    private lateinit var database: AppDatabase
    private lateinit var movieClipApi: MovieClipApi

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        movieClipApi = database.movieClipDao()
    }

    @Test
    fun testCreateClip() = runBlocking {
        //movieClipDao.insert(testMovieClips)
        //assertThat(movieClipDao.getAll().size, CoreMatchers.equalTo(3))
    }

    @After
    fun closeDb() {
        database.close()
    }
}