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

//    fun fetchYouTubeData(){
//        val call = YouTubeRetrofitInstance.youtubePlaylistItemsApi.getYouTubePlaylistItems()
//
//        call.enqueue(object : Callback<YouTubeResponse> {
//            override fun onResponse(
//                call: Call<YouTubeResponse>,
//                response: Response<YouTubeResponse>
//            ) {
//                if (response.isSuccessful) {
//                    Log.d("에엥", "${response.body()}")
//                    list = response.body()!!.items
//                    Log.d("에엥2", "$list")
//                    for (i in list) {
//                        i.snippet!!.title = if (i.snippet!!.title.toString()
//                                .contains("[playlist]")
//                        ) i.snippet!!.title.toString().replace("[playlist]", "")
//                            .trim() else i.snippet!!.title.toString().replace("(playlist)", "")
//                            .trim()
//                    }
//                        Log.d("에엥list", "$list")
//
//                } else {
//                    Log.d("통신성공, but 응답 문제", "아${response.errorBody()}")
//                }
//            }
//
//            override fun onFailure(call: Call<YouTubeResponse>, t: Throwable) {
//                throw IllegalStateException(t.message)
//            }
//        })
//        Log.d("list에", "$list")
//    }

}