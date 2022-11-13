package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.RListData
import com.example.myapplication.maniadbapi.adapter.CustomAdapter
import com.example.myapplication.databinding.ActivityRecyclableBinding

class Recyclable : AppCompatActivity() {

    val DataList = arrayListOf(
        RListData(R.drawable.cat, "0번"),
        RListData(R.drawable.cat, "1번"),
        RListData(R.drawable.cat, "2번"),
        RListData(R.drawable.cat, "3번"),
        RListData(R.drawable.cat, "4번"),
        RListData(R.drawable.cat, "5번"),
        RListData(R.drawable.cat, "6번"),
        RListData(R.drawable.cat, "7번"),
        RListData(R.drawable.cat, "8번"),
        RListData(R.drawable.cat, "9번"),
        RListData(R.drawable.cat, "10번"),
        RListData(R.drawable.cat, "11번"),
    )

    private lateinit var binding: ActivityRecyclableBinding
    private lateinit var manager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_recyclable)

        binding = ActivityRecyclableBinding.inflate(layoutInflater)

        setContentView(binding.root)

        manager = LinearLayoutManager(this)

        // Recyclable의 ui == this
        // 수직으로 스크롤 할 수 있게 해주는 코드
        binding.recyclerView.apply {
            adapter = CustomAdapter(DataList)
            layoutManager = manager
        }
    }
}