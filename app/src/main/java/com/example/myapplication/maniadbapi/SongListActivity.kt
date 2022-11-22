package com.example.myapplication.maniadbapi

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivitySongListBinding
import com.example.myapplication.maniadbapi.adapter.ManiaDBAdapter
import com.example.myapplication.maniadbapi.viewmodel.MainViewModel

class SongListActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySongListBinding
    private val mainViewModel by viewModels<MainViewModel>()
    private val mainAdapter = ManiaDBAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
//            lifecycleOwner = this@SongListActivity
//            activity = this@SongListActivity
////            vm = mainViewModel
//            recyclerView.adapter = mainAdapter
        }


//        binding = DataBindingUtil.setContentView(this, R.layout.activity_song_list)

        binding.apply {
            lifecycleOwner = this@SongListActivity
            activity = this@SongListActivity
//            vm = mainViewModel
            recyclerView.adapter = mainAdapter
        }

        mainViewModel.getAlbum("Remapping The Human Soul")

        initObserver()
    }

    fun getSong(){
        mainViewModel.getSong(binding.editKeyword.text.toString())
    }

    private fun initObserver(){
        mainViewModel.mySong.observe(this@SongListActivity){
            mainAdapter.submitList(it)
        }
    }
}