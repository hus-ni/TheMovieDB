package com.muhammadhusniabdillah.themoviedb.data.network.dto

import com.muhammadhusniabdillah.themoviedb.data.network.dto.MoviesDetails
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetMoviesResponse(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val movies: List<MoviesDetails>,
    @Json(name = "total_pages")
    val totalPages: Int
)
