package com.example.myapplication.ui.recyclerview

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.YoutubeActivity
import com.example.myapplication.databinding.RecommendItemBinding
import com.example.myapplication.envs.TAG_D
import com.example.myapplication.roomdb.db.WeplDatabase
import com.example.myapplication.roomdb.entity.Song
//import com.example.myapplication.maniadbapi.adapter.MyBindingAdapter.setImage
import com.example.myapplication.youtubeapi.Items
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RecommendAdapter(context: Context, youTubeItems: ArrayList<Items>) : RecyclerView.Adapter<RecommendAdapter.ViewHolder>() {
    private var itemList = youTubeItems
    private var cont = context
    private var weplDB = WeplDatabase.getDatabase(cont)


    inner class ViewHolder(private val binding: RecommendItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Items) {
            Glide.with(binding.root).load(item.snippet!!.thumbnails!!.high!!.url).centerCrop().into(binding.imageView2)
            binding.imageView2.clipToOutline = true
            binding.songTitle.text = item.snippet!!.title
        }
        fun keysBind(keys: List<String>){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecommendItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            // db 안의 노래 키워드 가져오기
            val keyList: Song = weplDB.songDao().getSongs(itemList[position].snippet!!.resourceId!!.videoId)
            Log.d(TAG_D, "keyList$keyList")
        }
        holder.bind(itemList[position])
        holder.itemView.setOnClickListener{
            Log.d(TAG_D, "ra-${itemList[position].snippet!!.resourceId!!.videoId}")
            var intent = Intent(cont,YoutubeActivity::class.java)
            intent.putExtra("videoId", itemList[position].snippet!!.resourceId!!.videoId)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount() = itemList.size
}