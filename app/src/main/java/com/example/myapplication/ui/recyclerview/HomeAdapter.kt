package com.example.myapplication.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RoundItemBinding
import com.example.myapplication.maniadbapi.adapter.ManiaDBAdapter
import com.example.myapplication.maniadbapi.adapter.MyBindingAdapter.setImage
import com.example.myapplication.maniadbapi.model.Item
import com.example.myapplication.ui.home.HomeViewModel

class HomeAdapter() : ListAdapter<Item, HomeAdapter.HomeViewHolder>(diffUtil) {


    inner class HomeViewHolder(private val binding: RoundItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : Item) {
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
//        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_text, parent, false)
//        return ViewHolder(v)
        val binding = RoundItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    // diffUtil 추가 (고유 값인 id로 비교 하는게 좋음. TEST 위해 title로 함)
    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<Item>(){
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }
}