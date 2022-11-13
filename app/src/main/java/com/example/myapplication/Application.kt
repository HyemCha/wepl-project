package com.example.myapplication

import android.app.Application
import com.example.myapplication.maniadbapi.repository.ManiaRepository

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        ManiaRepository.initialize(this)
    }
}