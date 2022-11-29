package com.example.myapplication.roomdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.roomdb.entity.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE first_name Like :fName LIMIT 1")
    suspend fun findByFirstName(fName: String): User

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("DELETE FROM user")
    suspend fun deleteAll()

    @Query("SELECT first_name FROM user WHERE(email Like :email) ")
    suspend fun isAlreadyJoined(email: String): String
}