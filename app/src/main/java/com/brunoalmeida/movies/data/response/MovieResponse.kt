package com.brunoalmeida.movies.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse (
    @Json(name = "id")
    val id: Int,

    @Json(name = "title")
    val title: String,

    @Json(name = "release_date")
    val releaseDate: String,

    @Json(name = "poster_path")
    val posterPath: String,

    @Json(name = "overview")
    val overview: String,

    @Json(name = "vote_average")
    val voteAverage: String?

//    @Json(name = "genre_ids")
//    val genreIds: ArrayList<String>?
)