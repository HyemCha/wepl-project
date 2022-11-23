package com.example.myapplication.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.envs.TAG
import com.example.myapplication.maniadbapi.model.Item
import com.example.myapplication.maniadbapi.repository.ManiaRepository
import com.example.myapplication.youtubeapi.YouTubeRepository
import com.example.myapplication.youtubeapi.YouTubeResponse
import com.example.myapplication.youtubeapi.YouTubeRetrofitInstance
import com.example.myapplication.youtubeapi.YouTubeService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {


    private val repository = ManiaRepository.get()
//    private val youTubeRepository = YouTubeRepository.get()

    private val _mySong : MutableLiveData<List<Item>> = MutableLiveData()
    val mySong: LiveData<List<Item>> get() = _mySong

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
    fun getSong(keyword: String){
        _state.value = false // 로딩
        viewModelScope.launch {
            repository.getSong(keyword).let { response ->
                if(response.isSuccessful){
                    val list = response.body()!!.channel!!.itemList
                    if (list != null) {
                        // 결과값 &nbsp; 제거
                        for(i in list){
                            i.title = i.title.replace("&nbsp;"," ")
                        }
                    }
                    _mySong.value = list
                }
                else{
                    Log.d(TAG, "getSong: ${response.code()}")
                }
                _state.value = true // 로딩 끝
            }
        }
    }

    fun getAlbum(keyword: String){
        viewModelScope.launch {
            repository.getAlbum(keyword).let { response ->
                if(response.isSuccessful){
                    var list = response.body()!!.channel!!.itemList
                    if (list != null) {
                        // 결과값 &nbsp; 제거
                        for(i in list){
                            i.album!!.trackList = i.album!!.trackList.replace("&nbsp;"," ").replace("/", "\n")
                                .replace("[Disc 1]","").replace("[Disc 2]","")
                        }
                    }
                    Log.d(TAG, "getAlbum: $list")
                }
            }
        }
    }
}