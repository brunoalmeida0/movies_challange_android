package com.brunoalmeida.movies.data.model

import androidx.room.*
import java.io.Serializable

@Entity(tableName = "movie")
//@TypeConverters(Converters::class)
data class Movie(
    @PrimaryKey
    val uuid: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @ColumnInfo(name = "overview")
    val overview: String?,

    @ColumnInfo(name = "vote_average")
    val voteAverage: String?

//    @ColumnInfo(name = "genre_ids")
//    val genreIds: String?
) : Serializable
//
//class Converters {
//    @TypeConverter
//    fun fromString(value: String): ArrayList<String> {
//        val listType = object : TypeToken<ArrayList<String>>() {
//
//        }.type
//        return Gson().fromJson(value, listType)
//    }
//
//    @TypeConverter
//    fun fromArrayLisr(list: ArrayList<String>): String {
//        val gson = Gson()
//        return gson.toJson(list)
//    }
//}