package com.muhammadhusniabdillah.themoviedb.data.local.dao

import androidx.room.*
import com.muhammadhusniabdillah.themoviedb.data.local.entity.UserTable
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: UserTable)

    @Query("select * from usertable where email = :email and password = :password limit 1")
    fun getUserData(email: String, password: String): Flow<UserTable>

    @Query("select * from usertable where email = :email")
    fun getUserData(email: String): Flow<UserTable>

    @Update
    suspend fun updateProfile(user: UserTable): Int
}