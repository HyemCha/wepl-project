package com.example.myapplication.ui.recyclerview

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.layout.Layout
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.YoutubeActivity
import com.example.myapplication.databinding.RoundItemBinding
import com.example.myapplication.envs.TAG_D
import com.example.myapplication.maniadbapi.adapter.MyBindingAdapter.setImage
import com.example.myapplication.youtubeapi.Items

class YouTubeAdapter(context: Context, youTubeItems: ArrayList<Items>) : RecyclerView.Adapter<YouTubeAdapter.ViewHolder>() {
    private var itemList = youTubeItems
    private var cont = context

    inner class ViewHolder(private val binding: RoundItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Items) {
            binding.imageView.clipToOutline = true
            item.snippet!!.thumbnails!!.high!!.url!!.let { binding.imageView.setImage(it) }
            binding.songTitle.text = item.snippet!!.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RoundItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
        Log.d(TAG_D, "에엥=${itemList[position]}")
        holder.itemView.setOnClickListener{
            var intent = Intent(cont, YoutubeActivity::class.java)
            intent.putExtra("videoId", itemList[position].snippet!!.resourceId!!.videoId)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount() = itemList!!.size
}