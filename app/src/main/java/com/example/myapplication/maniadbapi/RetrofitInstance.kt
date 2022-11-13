package com.example.myapplication.maniadbapi

import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory


object RetrofitInstance {
    const val BASE_URL = "http://www.maniadb.com/api/search/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
    }
    val api : ManiaDbApi by lazy {
        retrofit.create(ManiaDbApi::class.java)
    }
}