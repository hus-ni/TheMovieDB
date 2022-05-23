package com.muhammadhusniabdillah.themoviedb.data.repositories

import com.muhammadhusniabdillah.themoviedb.data.local.dao.UserDao
import com.muhammadhusniabdillah.themoviedb.data.local.entity.UserTable
import com.muhammadhusniabdillah.themoviedb.data.preferences.SessionData
import com.muhammadhusniabdillah.themoviedb.data.preferences.SessionPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val preferences: SessionPreferences
) {
    /** DATA STORE **/
    suspend fun deleteSession() {
        preferences.deleteSession()
    }

    fun getSession(): Flow<SessionData> {
        return preferences.getSession()
    }

    suspend fun saveSession(email: String, name: String) {
        preferences.saveSession(email, name)
    }

    /** ROOM **/
    fun userData(email: String): Flow<UserTable> {
        return userDao.getUserData(email)
    }

    fun userData(email: String, password: String): Flow<UserTable> {
        return userDao.getUserData(email, password)
    }

    suspend fun addNewUser(registrationData: UserTable) {
        userDao.addUser(user = registrationData)
    }
}