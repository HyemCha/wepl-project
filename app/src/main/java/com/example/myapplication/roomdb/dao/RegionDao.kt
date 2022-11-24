package com.example.myapplication.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.roomdb.entity.Region
import com.example.myapplication.roomdb.entity.RegionKeywords

@Dao
interface RegionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(region: Region)

//    @Query("SELECT * FROM region WHERE address LIKE '%' || :keyword || '%'")
//    fun getRegionByKeyword(keyword: String): Region

//    @Query("SELECT * FROM region JOIN region_keywords ON region.id = region_keywords.id")
//    fun loadRegionAndKeywords(regionId: Int): Map<Region, List<RegionKeywords>>

}