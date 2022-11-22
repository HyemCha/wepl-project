package com.example.myapplication.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.HomeActivity
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.ui.recyclerview.HomeAdapter
import com.example.myapplication.ui.recyclerview.RecyclerAdapter
import com.example.myapplication.youtubeapi.YouTubeRepository
import com.example.myapplication.youtubeapi.YouTubeResponse
import com.example.myapplication.youtubeapi.YouTubeRetrofitInstance
import com.example.myapplication.youtubeapi.YouTubeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    // 1. Context를 할당할 변수를 프로퍼티로 선언(어디서든 사용할 수 있게)
    private lateinit var homeActivity: HomeActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // 2. Context를 액티비티로 형변환해서 할당
        homeActivity = context as HomeActivity

    }
    private var _binding: FragmentHomeBinding? = null

    private lateinit var adapter: RecyclerAdapter
    private lateinit var recyclerView: RecyclerView

    private val youTubeRepository = YouTubeRepository()

//    private val homeFragmentViewModel by viewModels<HomeViewModel>()
    private val homeFragmentAdapter1 = HomeAdapter()
    private val homeFragmentAdapter2 = HomeAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // 프래그먼트의 레이아웃을 inflate하는 메서드
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        binding.apply {
            classic.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            classic.adapter = homeFragmentAdapter1
            classic.isHorizontalScrollBarEnabled = true

            jpop.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            jpop.adapter = homeFragmentAdapter2
            jpop.isHorizontalScrollBarEnabled = true
        }

        homeViewModel.apply{
            mySong.observe(viewLifecycleOwner, Observer {
                homeFragmentAdapter1.submitList(it)
                homeFragmentAdapter2.submitList(it)
            })
            getSong("interstella")
        }
//        homeViewModel.getAlbum("Remapping The Human Soul")
//        homeViewModel.getSong("classic")



        return root
    }


    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
//        val homeFragmentAdapter1: HomeAdapter
//        val layoutManager = LinearLayoutManager(context)
//        recyclerView = binding.recyclerviewHome
//        recyclerView.layoutManager = layoutManager
//        recyclerView.setHasFixedSize(true)
//        adapter = RecyclerAdapter(1234)
//        recyclerView.adapter = adapter

//        binding.recyclerviewHome.layoutManager = layoutManager
//        binding.recyclerviewHome.adapter = RecyclerAdapter(1234)
//        binding.recyclerviewHome.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        youTubeRepository.fetchYouTubeData()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}