package com.ryudith.lab6.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi

@Database(entities = [EntityUser::class], version = 2)
abstract class DatabaseUser : RoomDatabase() {
    abstract fun daoUser () : DAOUser

    companion object {
        @Volatile
        private var INSTANCE : DatabaseUser? = null

        @InternalCoroutinesApi
        fun getDB (context: Context) : DatabaseUser {
            if (INSTANCE != null) return INSTANCE as DatabaseUser

            synchronized(this) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, DatabaseUser::class.java, "db_user").fallbackToDestructiveMigration().build()
                return INSTANCE as DatabaseUser
            }
        }
    }
}