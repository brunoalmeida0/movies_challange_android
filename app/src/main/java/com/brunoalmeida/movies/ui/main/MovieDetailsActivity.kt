package com.brunoalmeida.movies.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.brunoalmeida.movies.R
import com.brunoalmeida.movies.data.model.Movie
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.content_movie_details.*
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        setSupportActionBar(toolbar)

        val movie = intent.getSerializableExtra(MOVIE) as Movie

        movieTitle.text = movie.title
        movieOverview.text = movie.overview
        movieVoteAverage.text = movie.voteAverage
        val url = "http://image.tmdb.org/t/p/w185" + movie.posterPath

        Picasso.get().load(url).into(moviePoster)

        fab.setOnClickListener { view ->
            MainActivity.database?.movieDao()?.insertMovies(movie)
        }

    }


    companion object {
        private const val MOVIE = "MOVIE"

        fun getStartIntent(context: Context, movie: Movie): Intent {
            return Intent(context, MovieDetailsActivity::class.java).apply {
                putExtra(MOVIE, movie)
            }
        }

    }

}
