package com.example.myapplication.ui.recommend

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.envs.*
import com.example.myapplication.roomdb.db.WeplDatabase
import com.example.myapplication.roomdb.entity.Region
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

class RecommendViewModel : ViewModel() {

    private lateinit var weplDB : WeplDatabase

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    private val _regionWithin1km = MutableLiveData<Region>()
    val regionWithin1km: LiveData<Region> = _regionWithin1km

    private val _pId = MutableLiveData<String>()
    val pId: LiveData<String> = _pId

    private val _tags = MutableLiveData<List<String>>()
    val tags: LiveData<List<String>> = _tags

    private val _distance = MutableLiveData<Double>()
    val distance: LiveData<Double> = _distance


    fun getTheRegionInTheScope(context: Context, userLon: Double?, userLat: Double?) {
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
                if(d!! <= DISTANCE){
                    _distance.postValue(d)
                    Log.d(TAG_D,"userLoc-return$i")
                    _regionWithin1km.postValue(i)
                    Log.d(TAG_D,"recViewModel-${regionWithin1km}")
                    getPId(i.id!!)

                    var ks: List<String>
                    ks = i.keywords!!.split(", ")
                    _tags.postValue(ks)
                }
                k++
            }
        }
    }

    fun getPId(myLoc: Int) {
        var pIdC = when (myLoc) {
            1 -> PLAYLIST_ID_1
            2 -> PLAYLIST_ID_2
            3 -> PLAYLIST_ID_3
            4 -> PLAYLIST_ID_4
            5 -> PLAYLIST_ID_5
            6 -> PLAYLIST_ID_6
            7 -> PLAYLIST_ID_7
            8 -> PLAYLIST_ID_8
            9 -> PLAYLIST_ID_9
            else -> {
                PLAYLIST_ID_1
            }
        }
        _pId.postValue(pIdC)
        Log.d(TAG_D, "pIdInRViewModel${pIdC}")
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