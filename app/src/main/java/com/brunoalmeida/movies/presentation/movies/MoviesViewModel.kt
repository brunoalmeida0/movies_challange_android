package com.brunoalmeida.movies.presentation.movies

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.brunoalmeida.movies.data.APIService
import com.brunoalmeida.movies.data.AppDatabase
import com.brunoalmeida.movies.data.model.Movie
import com.brunoalmeida.movies.data.response.PayloadResponse
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

    fun getMovies() {
        APIService.service.getMovies().enqueue(object : Callback<PayloadResponse> {
            override fun onResponse(
                call: Call<PayloadResponse>,
                response: Response<PayloadResponse>
            ) {
                if (response.isSuccessful) {
                    val movies: MutableList<Movie> = mutableListOf()

                    response.body()?.let { moviesPayload ->
                        for (payload in moviesPayload.results) {
                            val movie = Movie(
                                title = payload.title,
                                releaseDate = payload.releaseDate,
                                posterPath = payload.posterPath,
                                uuid = payload.id,
                                overview = payload.overview,
                                voteAverage = payload.voteAverage
                            )

                            movies.add(movie)
                        }
                    }
                    moviesLiveData.value = movies
                }
            }

            override fun onFailure(call: Call<PayloadResponse>, t: Throwable) {
                //TODO fazer tratamento de erro
            }

        })

    }

    fun getFavoritesMovies (){
        val moviesDB = MainActivity.database?.movieDao()?.getMovies()
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
                )

                favoritesMovies.add(movie)
            }
            favoritesMoviesLiveData.value = favoritesMovies

        }

    }

}