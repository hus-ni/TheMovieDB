package com.muhammadhusniabdillah.themoviedb.data.repositories

import com.muhammadhusniabdillah.themoviedb.data.network.MovieListServices
import com.muhammadhusniabdillah.themoviedb.data.network.dto.GetMoviesResponse
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class MovieRepositoryTest {

    private lateinit var apiServices: MovieListServices
    private lateinit var movieRepository: MovieRepository

    @Before
    fun setUp() {
        apiServices = mockk()
        movieRepository = MovieRepository(apiServices)
    }

    @Test
    fun getPopularMovies(): Unit = runBlocking {
        val response = mockk<GetMoviesResponse>()

        every {
            runBlocking {
                apiServices.getPopularMovies(20, 150000)
            }
        } returns response

        movieRepository.getPopularMovies()

        verify{
            runBlocking { apiServices.getPopularMovies(20, 150000) }
        }
    }

//    @Test
//    fun getTopRatedMovies() {
//    }
//
//    @Test
//    fun getUpcomingMovies() {
//    }
//
//    @Test
//    fun getMovieDetail() {
//    }
}