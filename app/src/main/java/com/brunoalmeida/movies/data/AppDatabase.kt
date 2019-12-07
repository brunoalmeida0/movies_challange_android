package com.brunoalmeida.movies.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.brunoalmeida.movies.data.dao.MovieDao
import com.brunoalmeida.movies.data.model.Movie

@Database(version = 1, entities = arrayOf(Movie::class))
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}