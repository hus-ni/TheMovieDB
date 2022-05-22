package com.muhammadhusniabdillah.themoviedb.data.repositories

import com.muhammadhusniabdillah.themoviedb.data.local.dao.UserDao
import com.muhammadhusniabdillah.themoviedb.data.local.entity.UserTable
import com.muhammadhusniabdillah.themoviedb.data.preferences.SessionPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val preferences: SessionPreferences
) {

    suspend fun deleteSession() {
        preferences.deleteSession()
    }

    fun getSession(): Flow<String> {
        return preferences.getSession()
    }

    suspend fun saveSession(email: String) {
        preferences.saveSession(email)
    }

    fun userLogin(email: String, password: String): Flow<UserTable> {
        return userDao.userLogin(email, password)
    }

    suspend fun addNewUser(registrationData: UserTable) {
        userDao.addUser(user = registrationData)
    }
}