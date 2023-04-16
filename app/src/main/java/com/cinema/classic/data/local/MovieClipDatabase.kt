package com.cinema.classic.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.cinema.classic.domain.model.MovieClip
import java.util.*

@Database(entities = [MovieClip::class],[YoutubeEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class MovieClipDatabase : RoomDatabase() {
    abstract val youtubeDao: YoutubeDao

    abstract val movieClipDao: MovieClipDao

    companion object {
        const val DATABASE_NAME = "clip_db"
    }
}

class Converters {
    @TypeConverter
    fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun datestampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }
}