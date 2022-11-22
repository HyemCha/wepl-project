package com.example.myapplication.youtubeapi

import com.example.myapplication.envs.BASE_URL_YOUTUBE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object YouTubeRetrofitInstance {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_YOUTUBE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val youtubePlaylistItems = retrofit.create(YouTubeService::class.java)
}