package com.example.myapplication.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.myapplication.roomdb.entity.RegionKeywords

@Dao
interface RegionKeywordsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(regionKeywords: RegionKeywords)
}