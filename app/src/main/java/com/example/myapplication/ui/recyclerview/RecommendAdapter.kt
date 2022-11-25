package com.example.myapplication.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RecommendItemBinding
import com.example.myapplication.maniadbapi.adapter.MyBindingAdapter.setImage
import com.example.myapplication.youtubeapi.Items
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RecommendAdapter(youTubeItems: ArrayList<Items>) : RecyclerView.Adapter<RecommendAdapter.ViewHolder>() {
    private var itemList = youTubeItems

    inner class ViewHolder(private val binding: RecommendItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Items) {
            item.snippet!!.thumbnails!!.high!!.url?.let { binding.imageView2.setImage(it) }
            binding.songTitle.text = item.snippet!!.title
//            binding.songTime.text =
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecommendItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            // db 안의 노래 키워드 가져오기

        }
        holder.bind(itemList[position])
    }

    override fun getItemCount() = itemList!!.size
}