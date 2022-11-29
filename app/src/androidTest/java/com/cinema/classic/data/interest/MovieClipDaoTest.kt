package com.cinema.classic.data.interest

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.cinema.classic.data.interest.util.testMovieClips
import com.cinema.classic.db.AppDatabase
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class MovieClipDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var movieClipDao: MovieClipDao

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        movieClipDao = database.movieClipDao()
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