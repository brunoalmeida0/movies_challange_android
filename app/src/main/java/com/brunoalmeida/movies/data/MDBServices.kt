package com.brunoalmeida.movies.data

import com.brunoalmeida.movies.data.response.PayloadResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MDBServices {

    @GET("movie/popular?api_key=1c965a2c8cbf7c08479498052276521b&language=en-US&page=1")
    fun getBooks(
//        @Query("api-key") apiKey: String = "riFAGsoTEEUGIuxWXWFIZab4ZSfFcbsF",
//        @Query("list") list: String = "hardcover-fiction"
    ): Call<PayloadResponse>

}