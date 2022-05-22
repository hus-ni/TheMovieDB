package com.muhammadhusniabdillah.themoviedb.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muhammadhusniabdillah.themoviedb.data.local.entity.UserTable
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: UserTable)

    @Query("select * from usertable where email = :email and password = :password")
    suspend fun userLogin(email: String, password: String): Flow<UserTable>
}