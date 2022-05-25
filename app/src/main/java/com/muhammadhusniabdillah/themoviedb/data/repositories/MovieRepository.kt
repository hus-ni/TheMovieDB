package com.muhammadhusniabdillah.themoviedb.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.muhammadhusniabdillah.themoviedb.data.network.MovieListServices
import com.muhammadhusniabdillah.themoviedb.data.network.dto.MoviesDetails
import com.muhammadhusniabdillah.themoviedb.data.network.paging.PopularMoviesPaging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.muhammadhusniabdillah.themoviedb.data.network.Response
import com.muhammadhusniabdillah.themoviedb.data.network.paging.TopRatedMoviesPaging
import com.muhammadhusniabdillah.themoviedb.data.network.paging.UpComingMoviesPaging
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieListServices: MovieListServices
) {
    fun getPopularMovies(): LiveData<PagingData<MoviesDetails>> {
        return Pager(
            config = PagingConfig(20),
            pagingSourceFactory = { PopularMoviesPaging(movieListServices = movieListServices) }
        ).liveData
    }

    fun getTopRatedMovies(): LiveData<PagingData<MoviesDetails>> {
        return Pager(
            config = PagingConfig(20),
            pagingSourceFactory = { TopRatedMoviesPaging(movieListServices = movieListServices) }
        ).liveData
    }

    fun getUpcomingMovies(): LiveData<PagingData<MoviesDetails>> {
        return Pager(
            config = PagingConfig(20),
            pagingSourceFactory = { UpComingMoviesPaging(movieListServices = movieListServices) }
        ).liveData
    }

    fun getMovieDetail(movieId: Int): Flow<Response<MoviesDetails>> {
        return flow {
            try {
                val response = movieListServices.getMovieDetail(movieId)
                emit(Response.Success(data = response))
            } catch (e: Exception) {
                emit(Response.Error(e))
            }
        }.flowOn(Dispatchers.IO)
    }
}