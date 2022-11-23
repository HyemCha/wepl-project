package com.example.myapplication.youtubeapi

import com.example.myapplication.envs.BASE_URL_YOUTUBE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object YouTubeRetrofitInstance {

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
        .baseUrl(BASE_URL_YOUTUBE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    }

    val youtubePlaylistItemsApi: YouTubeService by lazy {
        retrofit.create(YouTubeService::class.java)
    }
}