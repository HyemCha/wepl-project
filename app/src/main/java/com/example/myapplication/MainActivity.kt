package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.envs.TAG_D
import com.example.myapplication.maniadbapi.SongListActivity
import com.example.myapplication.roomdb.db.WeplDatabase
import com.example.myapplication.roomdb.entity.Region
import com.example.myapplication.roomdb.entity.RegionKeywords
import com.example.myapplication.youtubeapi.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 1km = 0.008993255058705971도
class MainActivity : AppCompatActivity() {

    private lateinit var weplDB: WeplDatabase

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, HomeActivity::class.java)
            startActivity(intent)
        }
        binding.lBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, SongListActivity::class.java)
            startActivity(intent)
        }


    }
}