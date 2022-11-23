package com.example.myapplication.youtubeapi

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YouTubeViewModel : ViewModel() {
    private val youTubeRepository = YouTubeRepository()
}