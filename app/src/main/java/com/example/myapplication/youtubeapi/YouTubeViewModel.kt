package com.example.myapplication.youtubeapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YouTubeViewModel : ViewModel() {
    private val youTubeRepository = YouTubeRepository()

    private val _youTubePlaylistItemsLiveData = MutableLiveData<ArrayList<Items>>()
    val youTubePlaylistItemsLiveData: LiveData<ArrayList<Items>> = _youTubePlaylistItemsLiveData

    fun refreshPlaylistItems(pId:String) {
        viewModelScope.launch {
            var response = youTubeRepository.fetchYouTubePlaylistItems(pId)
            if (response != null) {
                for (i in response) {
                    i.snippet!!.title  = if (i.snippet!!.title.toString()
                                .contains("[playlist]")
                        ) i.snippet!!.title.toString().replace("[playlist]", "")
                            .trim() else i.snippet!!.title.toString().replace("(playlist)", "")
                            .trim()
                }
            }
            _youTubePlaylistItemsLiveData.postValue(response)
        }
    }
}