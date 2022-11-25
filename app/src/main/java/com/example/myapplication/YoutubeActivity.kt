package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.databinding.ActivityYoutubeBinding
import com.example.myapplication.envs.TAG_D
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer

class YoutubeActivity : YouTubeBaseActivity() {

    private lateinit var binding: ActivityYoutubeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYoutubeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var videoId = intent.getStringExtra("videoId")
        Log.d(TAG_D, "yutubeAc-$videoId")

        val youtubeView = binding.youtubeView
        youtubeView.initialize("develop", object: YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                if (p1 != null) {
                    p1.cueVideo(videoId)
                }

                if (p1 != null) {
                    p1.setPlayerStateChangeListener(object:YouTubePlayer.PlayerStateChangeListener{
                        override fun onLoading() {}
                        override fun onLoaded(p0: String?) {
                            p1.play()
                        }
                        override fun onAdStarted() {}
                        override fun onVideoStarted() {}
                        override fun onVideoEnded() {}
                        override fun onError(p0: YouTubePlayer.ErrorReason?) {}
                    })
                }
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {

            }
        })
    }
}