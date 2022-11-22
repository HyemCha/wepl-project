package com.example.myapplication.youtubeapi

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YouTubeViewModel : ViewModel() {
//    private val retrofit = YouTubeRetrofitInstance.getInstance()
//    private val retrofitService: YouTubeService = retrofit.create(YouTubeService::class.java)
//    private val call: Call<YouTubeResponse>? = retrofitService?.getYouTubePlaylistItems()

//    fun getPlaylistItems() {
//        viewModelScope.launch{
//            call?.enqueue(object : Callback<YouTubeResponse> {
//                override fun onResponse(
//                    call: Call<YouTubeResponse>,
//                    response: Response<YouTubeResponse>
//                ) {
//                    // 응답 성공
//                    if (response.isSuccessful) {
//                        Log.d("Call-success", "success-${response.body()}")
//                    } else {
//                        Log.d("Call-error", "error-${response.errorBody()}")
//                    }
//                }
//
//                override fun onFailure(call: Call<YouTubeResponse>, t: Throwable) {
//                    Log.d("onFailue", "failure")
//                }
//            })
//        }
//    }
//    {
//        call?.enqueue(object : Callback<YouTubeResponse> {
//            override fun onResponse(
//                call: Call<YouTubeResponse>,
//                response: Response<YouTubeResponse>
//            ) {
//                // 응답 성공
//                if (response.isSuccessful) {
//                    Log.d("Call-success", "success-${response.body()}")
//                } else {
//                    Log.d("Call-error", "error-${response.errorBody()}")
//                }
//            }
//
//            override fun onFailure(call: Call<YouTubeResponse>, t: Throwable) {
//                Log.d("onFailue", "failure")
//            }
//        })
//    }
}