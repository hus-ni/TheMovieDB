package com.muhammadhusniabdillah.themoviedb.ui.profile

import androidx.lifecycle.*
import com.muhammadhusniabdillah.themoviedb.data.local.entity.UserTable
import com.muhammadhusniabdillah.themoviedb.data.preferences.SessionData
import com.muhammadhusniabdillah.themoviedb.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private var _userProfileData: MutableLiveData<UserTable> = MutableLiveData()
    val userProfileData: LiveData<UserTable> get() = _userProfileData

    private var _userSession: MutableLiveData<SessionData> = MutableLiveData()
    val userSession: LiveData<SessionData> get() = _userSession

    private val _picture: LiveData<String>? = picturePath()
    val picture = _picture

    init {
        getSession()
    }

    fun updateUserData(newData: UserTable) {
        viewModelScope.launch {
            userRepository.updateProfile(newData)
        }
    }

    fun loggingOut() {
        viewModelScope.launch {
            userRepository.deleteSession()
        }
    }

    private fun getSession() {
        viewModelScope.launch {
            userRepository.getSession().collectLatest {
                _userSession.value = it
            }
        }
    }

    fun updateUriProfilePics(imgUri: String) {
        viewModelScope.launch {
            userRepository.getSession().collectLatest {
                userRepository.updateProfilePics(imgUri, it.email)
            }
        }
    }

    private fun picturePath(): LiveData<String>? {
        var temp: LiveData<String>? = null
        viewModelScope.launch {
            userRepository.getSession().collectLatest {
                temp = userRepository.getPictures(it.email).asLiveData()
            }
        }
        return temp
    }

    fun userData(email: String) {
        viewModelScope.launch {
            userRepository.userData(email).collectLatest {
                _userProfileData.value = it
            }
        }
    }
}
