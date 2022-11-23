package com.example.myapplication.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.roomdb.entity.Region

@Dao
interface RegionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(region: Region)

    @Query("SELECT * FROM region WHERE address LIKE '%' || :keyword || '%'")
    fun getRegionByKeyword(keyword: String): Region
}