package com.muhammadhusniabdillah.themoviedb.data.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.muhammadhusniabdillah.themoviedb.data.network.MovieListServices
import com.muhammadhusniabdillah.themoviedb.data.network.dto.MoviesDetails


private const val FIRST_PAGE = 1

class UpComingMoviesPaging(
    private val movieListServices: MovieListServices
) : PagingSource<Int, MoviesDetails>() {
    override fun getRefreshKey(state: PagingState<Int, MoviesDetails>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesDetails> {
        return try {
            val position = params.key ?: FIRST_PAGE
            val responseUpcomingMovies =
                movieListServices.getUpcomingMovies(position, params.loadSize).movies
            LoadResult.Page(
                data = responseUpcomingMovies,
                prevKey = if (position == FIRST_PAGE) null else position - 1,
                nextKey = if (responseUpcomingMovies.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}