package com.muhammadhusniabdillah.themoviedb.data.repositories

import com.muhammadhusniabdillah.themoviedb.data.local.dao.UserDao
import com.muhammadhusniabdillah.themoviedb.data.local.entity.UserTable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun userLogin(email: String, password: String): Flow<UserTable> {
        return userDao.userLogin(email, password)
    }

    suspend fun addNewUser(registrationData: UserTable) {
        userDao.addUser(user = registrationData)
    }
}