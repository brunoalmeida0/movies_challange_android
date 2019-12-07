package com.brunoalmeida.movies.data.dao

import androidx.room.*
import com.brunoalmeida.movies.data.model.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie") fun getMovies(): List<Movie>

    @Insert(onConflict =  OnConflictStrategy.REPLACE) fun insertMovies(vararg movies: Movie)

    @Update fun updateMovie(movie: Movie)

    @Delete fun deleteMovie(movie: Movie)
}