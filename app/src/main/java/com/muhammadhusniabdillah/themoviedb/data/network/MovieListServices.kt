package com.muhammadhusniabdillah.themoviedb.data.network

import com.muhammadhusniabdillah.themoviedb.data.network.dto.GetMoviesResponse
import com.muhammadhusniabdillah.themoviedb.data.network.dto.MoviesDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieListServices {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): GetMoviesResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): GetMoviesResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): GetMoviesResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int
    ): MoviesDetails
}