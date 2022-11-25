package com.example.myapplication.youtubeapi

import android.util.Log
import com.example.myapplication.ui.recyclerview.YouTubeAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.*

class YouTubeRepository {
    var list: ArrayList<Items> = arrayListOf()

    suspend fun fetchYouTubePlaylistItems(playlistId: String): ArrayList<Items>? {
        val request = YouTubeRetrofitInstance.youtubePlaylistItemsApi.getYouTubePlaylistItems(playlistId = playlistId)

        if (request.isSuccessful) {
            return request.body()!!.items
        }

        return null
    }

}