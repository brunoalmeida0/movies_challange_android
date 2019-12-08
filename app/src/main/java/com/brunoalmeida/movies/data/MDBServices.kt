package com.brunoalmeida.movies.data

import com.brunoalmeida.movies.data.response.PayloadResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MDBServices {

    @GET("movie/popular?api_key=1c965a2c8cbf7c08479498052276521b&language=en-US&page=1")
    fun getMovies(): Call<PayloadResponse>

    @GET("search/multi?api_key=1c965a2c8cbf7c08479498052276521b")
    fun getMoviesSearch(@Query("query") key: String): Call<List<PayloadResponse>>

}