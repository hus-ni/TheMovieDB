package com.muhammadhusniabdillah.themoviedb.ui.authorization.login

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammadhusniabdillah.themoviedb.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    fun userLogin(email: String, password: String) {
        viewModelScope.launch {
            try {
                val dataUserByLogin = userRepository.userLogin(email, password)

                dataUserByLogin.collectLatest {
                    // save data store preferences
                    //it.email
                    //it.name
                }
            } catch (e: Exception) {
//                val errorMessage = "User Doesn't Exists!"
            }
        }
    }
}