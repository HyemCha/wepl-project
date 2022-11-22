package com.example.myapplication.youtubeapi

import android.util.Log
import retrofit2.*

class YouTubeRepository {

    fun fetchYouTubeData() {
        val call = YouTubeRetrofitInstance.youtubePlaylistItems.getYouTubePlaylistItems()

        call.enqueue(object : Callback<YouTubeResponse> {
            override fun onResponse(
                call: Call<YouTubeResponse>,
                response: Response<YouTubeResponse>
            ) {
                if (response.isSuccessful()) {
                    Log.d("에엥", "에에엥-${response.body()}")
                    val list = response.body()!!.items
                    Log.d("에엥2", "${list}")
                } else {
                    Log.d("통신성공, but 응답 문제", "아${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<YouTubeResponse>, t: Throwable) {
                throw IllegalStateException(t.message)
            }
        })
    }

}