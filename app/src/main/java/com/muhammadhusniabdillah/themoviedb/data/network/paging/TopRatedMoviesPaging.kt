package com.muhammadhusniabdillah.themoviedb.data.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.muhammadhusniabdillah.themoviedb.data.network.MovieListServices
import com.muhammadhusniabdillah.themoviedb.data.network.dto.MoviesDetails

private const val FIRST_PAGE = 1

class TopRatedMoviesPaging(
    private val movieListServices: MovieListServices
) : PagingSource<Int, MoviesDetails>() {
    override fun getRefreshKey(state: PagingState<Int, MoviesDetails>): Int? {
        return state.anchorPosition?.let {
            val anchor = state.closestPageToPosition(it)
            anchor?.prevKey?.plus(1) ?: anchor?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesDetails> {
        return try {
            val position = params.key ?: FIRST_PAGE
            val responseTopRatedMovies =
                movieListServices.getTopRatedMovies(position, params.loadSize).movies
            LoadResult.Page(
                data = responseTopRatedMovies,
                prevKey = if (position == FIRST_PAGE) null else position - 1,
                nextKey = if (responseTopRatedMovies.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}