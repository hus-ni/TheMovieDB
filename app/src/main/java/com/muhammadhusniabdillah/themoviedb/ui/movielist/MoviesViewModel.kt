package com.muhammadhusniabdillah.themoviedb.ui.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.muhammadhusniabdillah.themoviedb.data.network.Response
import com.muhammadhusniabdillah.themoviedb.data.network.dto.MoviesDetails
import com.muhammadhusniabdillah.themoviedb.data.repositories.MovieRepository
import com.muhammadhusniabdillah.themoviedb.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val movieRepository: MovieRepository
) : ViewModel() {

    val popularMovies: LiveData<PagingData<MoviesDetails>> =
        movieRepository.getPopularMovies().cachedIn(viewModelScope)
    val topRatedMovies: LiveData<PagingData<MoviesDetails>> =
        movieRepository.getTopRatedMovies().cachedIn(viewModelScope)
    val upcomingMovies: LiveData<PagingData<MoviesDetails>> =
        movieRepository.getUpcomingMovies().cachedIn(viewModelScope)

    private var _name: MutableLiveData<String> = MutableLiveData()
    val name: LiveData<String> get() = _name

    init {
        getWelcomeName()
    }

    private fun getWelcomeName() {
        viewModelScope.launch {
            userRepository.getSession().collectLatest {
                _name.value = it.name
            }
        }
    }

    /** Detail Fragment View Model**/

    private val _details: MutableLiveData<MoviesDetails?> = MutableLiveData()
    val details: LiveData<MoviesDetails?> get() = _details

    private var errorMessage: String? = null

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            movieRepository.getMovieDetail(movieId).collectLatest {
                when (it) {
                    is Response.Success -> _details.value = it.data
                    is Response.Error -> errorMessage = it.exception.message
                }
            }
        }
    }
}