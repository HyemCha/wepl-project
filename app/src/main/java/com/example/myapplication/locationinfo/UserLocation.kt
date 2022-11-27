package com.example.myapplication.locationinfo

import android.content.Context
import android.util.Log
import com.example.myapplication.envs.TAG_D
import com.example.myapplication.roomdb.db.WeplDatabase
import com.example.myapplication.roomdb.entity.Region
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

class UserLocation() {
    private lateinit var weplDB : WeplDatabase

    fun getTheRegionInTheScope(context: Context, userLon: Double?, userLat: Double?):Region? {
        var resultRegion: Region? = null

        weplDB = WeplDatabase.getDatabase(context)

        var regionList : List<Region>? = null
        GlobalScope.launch(Dispatchers.IO) {
            regionList = weplDB.regionDao().getAllRegion()
            Log.d(TAG_D, "UserLoc-$regionList")
            var k = 1
            for (i in regionList!!) {
                var d = calcDistance(i, userLon!!, userLat!!)
                Log.d(TAG_D, "distance-$d")
                if(d!! <=1){
                    Log.d(TAG_D,"userLoc-return$i")
                    resultRegion = i
                }
                k++
            }
        }

        return resultRegion
    }

    fun calcDistance(r: Region, userLon: Double, userLat: Double): Double? {
        var distance: Double? = null

        distance = 6371 * acos(
            cos(Math.toRadians(userLat)) * cos(Math.toRadians(r.latitude!!)) * cos(Math.toRadians(r.longitude!!)
                -Math.toRadians(userLon)) + sin(Math.toRadians(userLat)) * sin(Math.toRadians(r.latitude))
        )

        return distance
    }
}