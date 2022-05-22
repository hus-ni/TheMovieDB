package com.muhammadhusniabdillah.themoviedb.ui.authorization.login

import androidx.lifecycle.*
import com.muhammadhusniabdillah.themoviedb.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private var _errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String> get() = _errorMessage

    private var _loginState: MutableLiveData<Boolean> = MutableLiveData()
    val loginState: LiveData<Boolean> get() = _loginState

    private var _loginSessionByEmail: MutableLiveData<String> = MutableLiveData()
    val loginSessionByEmail: LiveData<String> get() = _loginSessionByEmail

    fun userLogin(email: String, password: String) {
        viewModelScope.launch {
            // try catch must do something that chance of error / failing is present.
            // otherwise its just gonna always ignore the 'catch' section
            try {
                val dataUserByLogin = userRepository.userLogin(email, password)
                dataUserByLogin.collectLatest { dataUser ->
                    when {
                        dataUser.email.isNotEmpty() -> {
                            _loginState.value = true
                            userRepository.saveSession(dataUser.email)
                        }
                        else -> {
                            _loginState.value = false
                        }
                    }
                    // save data store preferences
                    //it.email
                    //it.name
                }
            } catch (e: Exception) {
                _errorMessage.value = "Invalid Email or Password!"
            }
        }
    }

    fun getSession() {
        viewModelScope.launch {
            _loginSessionByEmail.value = userRepository.getSession().first()
        }
    }
}