package com.example.myapplication.roomdb.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.roomdb.dao.UserDao
import com.example.myapplication.roomdb.entity.User

@Database(entities = [User::class], version = 1)
abstract class WeplDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE : WeplDatabase? = null
        // 특정 컨텍스트로 이동하고 컨텍스트를 인수로 사용
        fun getDatabase(context: Context): WeplDatabase{
            val tempInstance = INSTANCE
            // instance가 null이 아니면 기존의 instance 반환
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeplDatabase::class.java,
                    "wepl_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}