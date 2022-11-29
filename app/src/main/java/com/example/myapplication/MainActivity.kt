package com.example.myapplication

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.roomdb.db.WeplDatabase
import java.security.MessageDigest

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
            intent.putExtra("firstActivity", "2")

            startActivity(intent)
        }
        binding.lBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, HomeActivity::class.java)
            intent.putExtra("firstActivity", "1")
            startActivity(intent)
        }

        
    }
    fun getAppKeyHash() {
        try {
            val info =
                packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                var md: MessageDigest
                md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val something = String(Base64.encode(md.digest(), 0))
                Log.e("Hash key", something)
            }
        } catch (e: Exception) {

            Log.e("name not found", e.toString())
        }
    }
}