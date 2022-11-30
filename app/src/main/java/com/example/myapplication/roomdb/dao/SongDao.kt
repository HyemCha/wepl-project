package com.example.myapplication.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.roomdb.entity.Song


@Dao
interface SongDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(song: Song)

    @Query("Select * from song where region_id=:regionId")
    fun getSongs(regionId: String?): Song
}
