package com.muhammadhusniabdillah.themoviedb.data.network.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class MoviesDetails(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "overview") val overview: String,
    @Json(name = "poster_path") val posterPath: String,
    @Json(name = "backdrop_path") val backdropPath: String,
    @Json(name = "vote_average") val rating: Float,
    @Json(name = "release_date") val releaseDate: String
) : Parcelable