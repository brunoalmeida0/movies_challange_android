package com.brunoalmeida.movies.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.brunoalmeida.movies.R
import com.brunoalmeida.movies.data.model.Movie

import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.content_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val movietest = Movie(
                uuid = 1235,
                title = "title teste",
                releaseDate = "teste release date",
                posterPath = "poster path teste",
                overview = "overview teste",
                voteAverage = "vote average teste"
//            genreIds = arrayListOf("Teste", "teste2")
            )

            MainActivity.database?.movieDao()?.insertMovies(movietest)
            val teste = MainActivity.database?.movieDao()?.getMovies()
            Log.d("TESTE_FILME_FAVORITO", "filme salvo como favorito ao clicatar no floatbuttom" + teste);
        }

        bookDetailsTitle.text = intent.getStringExtra(EXTRA_TITLE)
        bookDetailsDescription.text = intent.getStringExtra(EXTRA_DESCRIPTION)
    }


    companion object {
        private const val EXTRA_TITLE = "EXTRA_TITLE"
        private const val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"

        fun getStartIntent(context: Context, title: String, description: String): Intent {
            return Intent(context, MovieDetailsActivity::class.java).apply {
                putExtra(EXTRA_TITLE, title)
                putExtra(EXTRA_DESCRIPTION, description)
            }
        }

    }

}
