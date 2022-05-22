package com.muhammadhusniabdillah.themoviedb.ui.authorization.register

import androidx.lifecycle.*
import com.muhammadhusniabdillah.themoviedb.data.local.entity.UserTable
import com.muhammadhusniabdillah.themoviedb.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    fun getRegistrationData(name: String, email: String, password: String) {
        val data = UserTable(null, name, email, password, null)
        viewModelScope.launch {
            userRepository.addNewUser(data)
        }
    }
}

