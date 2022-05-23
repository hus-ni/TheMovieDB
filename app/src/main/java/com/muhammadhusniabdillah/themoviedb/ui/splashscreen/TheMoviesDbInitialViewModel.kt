package com.muhammadhusniabdillah.themoviedb.ui.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammadhusniabdillah.themoviedb.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TheMoviesDbInitialViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _loginState: MutableLiveData<Boolean> = MutableLiveData()
    val loginState: LiveData<Boolean> get() = _loginState

    init {
        getSession()
    }

    private fun getSession(){
        viewModelScope.launch {
            userRepository.getSession().collectLatest {
                _loginState.value = it.isNotBlank()
            }
        }
    }
}
