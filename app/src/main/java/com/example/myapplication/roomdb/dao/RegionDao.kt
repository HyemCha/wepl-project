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

//    @Query(
//        "SELECT *, (6371*acos(cos(radians(37.4685225))*cos(radians(latitude))*cos(radians(longitude)-" +
//                "radians(126.8943311))+sin(radians(37.4685225))*sin(radians(latitude)))) " +
//                "AS distance FROM region HAVING distance <= 1 ORDER BY distance LIMIT 0,300"
//    )
//    suspend fun getRegionByLocation()
}