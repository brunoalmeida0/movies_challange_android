package com.brunoalmeida.movies.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse (
    @Json(name = "id")
    val id: Int,

    @Json(name = "poster_path")
    val posterPath: String,

    @Json(name = "overview")
    val overview: String,

    @Json(name = "title")
    val title: String,

    @Json(name = "release_date")
    val releaseDate: String
)