package com.example.myapplication.maniadbapi.repository

import android.content.Context
import com.example.myapplication.maniadbapi.RetrofitInstance
import com.example.myapplication.maniadbapi.model.ManiaAlbumResponse
import com.example.myapplication.maniadbapi.model.ManiaDBClientResponse
import retrofit2.Response

class ManiaRepository private constructor(context: Context){

    suspend fun getSong(keyword: String) : Response<ManiaDBClientResponse> {
        return RetrofitInstance.api.getSong(keyword)
    }

    suspend fun getAlbum(keyword: String) : Response<ManiaAlbumResponse>{
        return RetrofitInstance.api.getAlbum(keyword)
    }

    // 싱글톤
    companion object {
        private var INSTANCE: ManiaRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = ManiaRepository(context)
            }
        }

        fun get(): ManiaRepository {
            return INSTANCE ?:
            throw IllegalStateException("Repository must be initialized")
        }
    }
}