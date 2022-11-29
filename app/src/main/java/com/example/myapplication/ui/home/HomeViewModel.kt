package com.example.myapplication.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.envs.TAG
import com.example.myapplication.youtubeapi.YouTubeRepository
import com.example.myapplication.youtubeapi.YouTubeResponse
import com.example.myapplication.youtubeapi.YouTubeRetrofitInstance
import com.example.myapplication.youtubeapi.YouTubeService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {


    private var _state : MutableLiveData<Boolean> = MutableLiveData()
    val state : LiveData<Boolean> get() = _state

    init {
        _state.value = true
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    // YouTube Data


    //    private val retrofit = YouTubeRetrofitInstance.getInstance()
//    private val retrofitService: YouTubeService = retrofit.create(YouTubeService::class.java)
//    private val call: Call<YouTubeResponse>? = retrofitService?.getYouTubePlaylistItems()
//
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
//    fun getPlaylistItems() {
//        viewModelScope.launch {
//            youTubeRepository.getPlaylistItems().let { response ->
//                if (response.isSuccessful) {
//                    Log.d("getplaylistItems-s", "${response.body()}")
//                }else{
//                    Log.d("playlist-fㅜㅜ", "${response.code()}")
//                }
//            }
//        }
//    }

}