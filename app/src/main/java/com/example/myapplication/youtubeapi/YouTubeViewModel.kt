package com.example.myapplication.youtubeapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.envs.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YouTubeViewModel : ViewModel() {
    private val youTubeRepository = YouTubeRepository()

    private val _youTubePlaylistItemsLiveData = MutableLiveData<ArrayList<Items>>()
    val youTubePlaylistItemsLiveData: LiveData<ArrayList<Items>> = _youTubePlaylistItemsLiveData
    private val _youTubePlaylistItemsLiveData2 = MutableLiveData<ArrayList<Items>>()
    val youTubePlaylistItemsLiveData2: LiveData<ArrayList<Items>> = _youTubePlaylistItemsLiveData2
    private val _youTubePlaylistItemsLiveData3 = MutableLiveData<ArrayList<Items>>()
    val youTubePlaylistItemsLiveData3: LiveData<ArrayList<Items>> = _youTubePlaylistItemsLiveData3
    private val _youTubePlaylistItemsLiveData4 = MutableLiveData<ArrayList<Items>>()
    val youTubePlaylistItemsLiveData4: LiveData<ArrayList<Items>> = _youTubePlaylistItemsLiveData4
    private val _youTubePlaylistItemsLiveData5 = MutableLiveData<ArrayList<Items>>()
    val youTubePlaylistItemsLiveData5: LiveData<ArrayList<Items>> = _youTubePlaylistItemsLiveData5
    private val _youTubePlaylistItemsLiveData6 = MutableLiveData<ArrayList<Items>>()
    val youTubePlaylistItemsLiveData6: LiveData<ArrayList<Items>> = _youTubePlaylistItemsLiveData6
    private val _youTubePlaylistItemsLiveData7 = MutableLiveData<ArrayList<Items>>()
    val youTubePlaylistItemsLiveData7: LiveData<ArrayList<Items>> = _youTubePlaylistItemsLiveData7
    private val _youTubePlaylistItemsLiveData8 = MutableLiveData<ArrayList<Items>>()
    val youTubePlaylistItemsLiveData8: LiveData<ArrayList<Items>> = _youTubePlaylistItemsLiveData8
    private val _youTubePlaylistItemsLiveData9 = MutableLiveData<ArrayList<Items>>()
    val youTubePlaylistItemsLiveData9: LiveData<ArrayList<Items>> = _youTubePlaylistItemsLiveData9
    private val _youTubePlaylistItemsLiveData10 = MutableLiveData<ArrayList<Items>>()
    val youTubePlaylistItemsLiveData10: LiveData<ArrayList<Items>> = _youTubePlaylistItemsLiveData10


    fun refreshPlaylistItems(pId:String) {
        Log.d(TAG_D, "YouTubeViewModel-pId$pId")
        viewModelScope.launch {
            var response = youTubeRepository.fetchYouTubePlaylistItems(pId)
            if (response != null) {
                for (i in response) {
                    i.snippet!!.title  = if (i.snippet!!.title.toString()
                                .contains("[playlist]")
                        ) i.snippet!!.title.toString().replace("[playlist]", "")
                            .trim() else if(i.snippet!!.title.toString()
                            .contains("(playlist)")) i.snippet!!.title.toString().replace("(playlist)", "")
                            .trim() else if(i.snippet!!.title.toString()
                            .contains("[Playlist]")) i.snippet!!.title.toString().replace("[Playlist]","") else i.snippet!!.title
                }
            }
            when (pId) {
                HOME_PID_1 -> {
                    _youTubePlaylistItemsLiveData.postValue(response)
                }
                HOME_PID_2 -> {
                    _youTubePlaylistItemsLiveData2.postValue(response)
                }
                HOME_PID_3 -> {
                    _youTubePlaylistItemsLiveData3.postValue(response)
                }
            }

            if(pId == PLAYLIST_ID_1 || pId == PLAYLIST_ID_2 || pId == PLAYLIST_ID_3 || pId == PLAYLIST_ID_4
                || pId == PLAYLIST_ID_5 || pId == PLAYLIST_ID_6 || pId == PLAYLIST_ID_7 || pId == PLAYLIST_ID_8
                || pId == PLAYLIST_ID_9){
                _youTubePlaylistItemsLiveData.postValue(response)
            }
        }
    }
}