package com.example.myapplication.youtubeapi

import com.example.myapplication.envs.YOUTUBE_API_KEY
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeService {
    //video resource endpoint
    @GET("youtube/v3/playlistItems")
    suspend fun fetchYoutubeSearchResultInfo(
        @Query("key") apiKey: String = YOUTUBE_API_KEY,
        @Query("fields") fields: String = "items(id,snippet(title,thumbnails,channelTitle))",
        @Query("part") part: String = "snippet",
        @Query("maxResult") maxResult: String = "10",
        @Query("playlistId") playlistId: String = "PLXItnL0261EJGhR1_h3zuQBNtopFFXg0u"
    ): Response<YouTubeResponse>?

    @GET("youtube/v3/playlistItems")
    suspend fun getYouTubePlaylistItems(
        @Query("key") apiKey: String = YOUTUBE_API_KEY,
//        @Query("fields") fields: String = "items(id,snippet(title,thumbnails,channelTitle))",
        @Query("part") part: String = "snippet",
        @Query("maxResults") maxResults: String = "10",
        @Query("playlistId") playlistId: String = ""
    ) : Response<YouTubeResponse>

    suspend fun getYouTubeSongsByKeywords(
        @Query("key") apiKey: String = YOUTUBE_API_KEY,
//        @Query("fields") fields: String = "items(id,snippet(title,thumbnails,channelTitle))",
        @Query("part") part: String = "snippet",
        @Query("maxResults") maxResults: String = "10",
        @Query("playlistId") playlistId: String = ""
    ):Response<YouTubeResponse>

}