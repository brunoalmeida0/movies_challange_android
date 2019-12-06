package com.brunoalmeida.movies.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.brunoalmeida.movies.data.APIService
import com.brunoalmeida.movies.data.model.Movie
import com.brunoalmeida.movies.data.response.PayloadResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        "Hello world from section teste: $it"
    }
    val moviesLiveData: MutableLiveData<List<Movie>> = MutableLiveData()

    fun setIndex(index: Int) {
        _index.value = index
    }

    fun getMovies() {
//        moviesLiveData.value = getFakeMovies()
        APIService.service.getBooks().enqueue(object : Callback<PayloadResponse> {
            override fun onResponse(
                call: Call<PayloadResponse>,
                response: Response<PayloadResponse>
            ) {
                Log.d("a", "pegou o recycle do layout" + response);
                if (response.isSuccessful) {
                    val movies: MutableList<Movie> = mutableListOf()

                    response.body()?.let { moviesPayload ->
                        for (payload in moviesPayload.results) {
                            val movie = Movie(
                                title = payload.title,
                                releaseDate = payload.releaseDate
                            )

                            movies.add(movie)
                        }
                    }
                    moviesLiveData.value = movies
                }
            }

            override fun onFailure(call: Call<PayloadResponse>, t: Throwable) {
                Log.d("a", "pegou o recycle do layout erro" + t);
            }


        })

    }


    fun getFakeMovies(): List<Movie> {
        return listOf(
            Movie("Title 1", "17/09/1996"),
            Movie("Title 2", "17/09/1996"),
            Movie("Title 3", "17/09/1996")
        )
    }
}