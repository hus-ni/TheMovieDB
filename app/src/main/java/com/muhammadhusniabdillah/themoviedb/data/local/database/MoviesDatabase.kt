package com.muhammadhusniabdillah.themoviedb.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.muhammadhusniabdillah.themoviedb.data.local.dao.UserDao
import com.muhammadhusniabdillah.themoviedb.data.local.entity.UserTable

@Database(
    entities = [UserTable::class],
    version = 1,
    exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

//private lateinit var INSTANCE: MoviesDatabase
//
//fun getDatabase(context: Context): MoviesDatabase {
//    synchronized(MoviesDatabase::class.java) {
//        if (!::INSTANCE.isInitialized) {
//            INSTANCE = Room.databaseBuilder(
//                context.applicationContext,
//                MoviesDatabase::class.java,
//                "movies.db"
//            ).fallbackToDestructiveMigration().build()
//        }
//    }
//    return INSTANCE
//}