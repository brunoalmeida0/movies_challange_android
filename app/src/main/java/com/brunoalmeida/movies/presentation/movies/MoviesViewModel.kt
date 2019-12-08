package com.brunoalmeida.movies.presentation.movies

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Query
import androidx.room.Room
import com.brunoalmeida.movies.data.APIService
import com.brunoalmeida.movies.data.AppDatabase
import com.brunoalmeida.movies.data.model.Movie
import com.brunoalmeida.movies.data.response.PayloadResponse
import com.brunoalmeida.movies.presentation.movies.fragments.MoviesFragment
import com.brunoalmeida.movies.ui.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    val moviesLiveData: MutableLiveData<List<Movie>> = MutableLiveData()
    val favoritesMoviesLiveData: MutableLiveData<List<Movie>> = MutableLiveData()

    fun setIndex(index: Int) {
        _index.value = index
    }

    fun getMovies(page: Int, moviesFragment: MoviesFragment) {
        APIService.service.getMovies(page.toString()).enqueue(object : Callback<PayloadResponse> {
            override fun onResponse(
                call: Call<PayloadResponse>,
                response: Response<PayloadResponse>
            ) {
                successResponse(page, response, moviesFragment)
            }

            override fun onFailure(call: Call<PayloadResponse>, t: Throwable) {
                //TODO fazer tratamento de erro
            }

        })

    }

    fun searchMovies(page: Int, titleForSearch: String, moviesFragment: MoviesFragment) {
        APIService.service.getMoviesSearch(page.toString(), titleForSearch)
            .enqueue(object : Callback<PayloadResponse> {
                override fun onResponse(
                    call: Call<PayloadResponse>,
                    response: Response<PayloadResponse>
                ) {
                    successResponse(page, response, moviesFragment)
                }

                override fun onFailure(call: Call<PayloadResponse>, t: Throwable) {
                    //TODO fazer tratamento de erro
                }

            })
    }

    fun successResponse(
        page: Int,
        response: Response<PayloadResponse>,
        moviesFragment: MoviesFragment
    ) {
        if (response.isSuccessful) {
            val movies: MutableList<Movie> = mutableListOf()

            response.body()?.let { moviesPayload ->
                for (payload in moviesPayload.results) {
                    val movie = Movie(
                        title = payload.title,
                        releaseDate = payload.releaseDate,
                        posterPath = payload.posterPath,
                        uuid = payload.uuid,
                        overview = payload.overview,
                        voteAverage = payload.voteAverage
//                        genreIds = payload.genreIds.toString()
                    )

                    movies.add(movie)
                }
            }
            if (page > 1) {
                moviesFragment.showMoreMovieList(movies)
            } else {
                moviesLiveData.value = movies
            }
        }
    }

    fun getFavoritesMovies(query: String?) {
        val moviesDB =
            if (query != null) MainActivity.database?.movieDao()?.filterMoviesByTitleOrReleaseDate(
                query
            ) else MainActivity.database?.movieDao()?.getMovies()

        val favoritesMovies: MutableList<Movie> = mutableListOf()
        moviesDB?.let { moviesDB ->
            for (payload in moviesDB) {
                val movie = Movie(
                    title = payload.title,
                    releaseDate = payload.releaseDate,
                    posterPath = payload.posterPath,
                    uuid = payload.uuid,
                    overview = payload.overview,
                    voteAverage = payload.voteAverage
//                    genreIds = payload.genreIds.toString()
                )

                favoritesMovies.add(movie)
            }
            favoritesMoviesLiveData.value = favoritesMovies

        }

    }

}