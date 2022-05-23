package com.muhammadhusniabdillah.themoviedb.ui.authorization.login

import androidx.lifecycle.*
import com.muhammadhusniabdillah.themoviedb.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private var _errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String> get() = _errorMessage

    private var _loginSessionByEmail: MutableLiveData<Boolean> = MutableLiveData()
    val loginSessionByEmail: LiveData<Boolean> get() = _loginSessionByEmail

    fun userLogin(email: String, password: String) {
        viewModelScope.launch {
            // try catch must do something that chance of error / failing is present.
            // otherwise its just gonna always ignore the 'catch' section
            try {
                val dataUserByLogin = userRepository.userData(email, password)
                dataUserByLogin.collectLatest { dataUser ->
                    when {
                        dataUser.email.isNotBlank() -> {
                            userRepository.saveSession(dataUser.email, dataUser.name)
                            _loginSessionByEmail.value = true
                        }
                        else -> {
                            _loginSessionByEmail.value = false
                            _errorMessage.value = "Error Session Empty!"
                        }
                    }
                }
            } catch (e: Exception) {
                _errorMessage.value = "Invalid Email or Password!"
            }
        }
    }
}