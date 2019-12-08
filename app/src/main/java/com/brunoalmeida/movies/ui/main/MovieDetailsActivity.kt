package com.brunoalmeida.movies.ui.main

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.brunoalmeida.movies.R
import com.brunoalmeida.movies.data.model.Movie
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.content_movie_details.*
import kotlinx.android.synthetic.main.movie_item.view.*
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.MenuItem
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class MovieDetailsActivity : AppCompatActivity() {

    var isFavorite: Boolean = false

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        fab.setImageResource(android.R.drawable.star_off)
        val movieDAO = MainActivity.database?.movieDao()

        val movie = intent.getSerializableExtra(MOVIE) as Movie
        supportActionBar!!.title = movie.title

        val movieFavorite = movieDAO?.findMovieById(movie.uuid)

        if (movieFavorite!!.isPresent) {
            fab.setImageResource(android.R.drawable.star_on)
            isFavorite = true
        }

        movieOverview.text = movie.overview
        movieVoteAverage.text = movie.voteAverage
        val url = "http://image.tmdb.org/t/p/w185" + movie.posterPath

        Picasso.get().load(url).into(moviePoster)


        fab.setOnClickListener {

            if (isFavorite) {
                movieDAO.deleteMovie(movie)
                fab.setImageResource(android.R.drawable.star_off)
            } else {
                movieDAO.insertMovies(movie)
                fab.setImageResource(android.R.drawable.star_on)

            }

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //Botão adicional na ToolBar
        when (item.getItemId()) {
            android.R.id.home  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
            -> {
                startActivity(
                    Intent(
                        this,
                        MainActivity::class.java
                    )
                )  //O efeito ao ser pressionado do botão (no caso abre a activity)
                finishAffinity()  //Método para matar a activity e não deixa-lá indexada na pilhagem
            }
            else -> {
            }
        }
        return true
    }

    override fun onBackPressed() { //Botão BACK padrão do android
        startActivity(
            Intent(
                this,
                MainActivity::class.java
            )
        ) //O efeito ao ser pressionado do botão (no caso abre a activity)
        finishAffinity() //Método para matar a activity e não deixa-lá indexada na pilhagem
        return
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
