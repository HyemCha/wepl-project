package com.example.myapplication.ui.recyclerview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.databinding.FrameTextviewBinding
import java.util.*
import kotlin.random.Random

class RecyclerAdapter(seed: Int) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
    private var random = Random(seed)

    // itemView는 FrameLayout컴포넌트이다.
//    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        //        var randomText :TextView = itemView.findViewById(R.id.randomText)
//    }
    inner class ViewHolder(private val binding: FrameTextviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(num:String) {
            binding.randomText.text = num
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_text, parent, false)
//        return ViewHolder(v)
        val binding = FrameTextviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.randomText.text = random.nextInt().toString()
        holder.bind(random.nextInt().toString())
    }

    override fun getItemCount() = 100
}