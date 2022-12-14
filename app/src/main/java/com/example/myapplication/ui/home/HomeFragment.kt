package com.example.myapplication.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.HomeActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.envs.HOME_PID_3
import com.example.myapplication.ui.recyclerview.YouTubeAdapter
import com.example.myapplication.youtubeapi.YouTubeViewModel

class HomeFragment : Fragment() {

    // 1. Context를 할당할 변수를 프로퍼티로 선언(어디서든 사용할 수 있게)
    private lateinit var homeActivity: HomeActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // 2. Context를 액티비티로 형변환해서 할당
        homeActivity = context as HomeActivity
    }

    private var _binding: FragmentHomeBinding? = null

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
        val youTubeViewModel: YouTubeViewModel by lazy {
            ViewModelProvider(this)[YouTubeViewModel::class.java]
        }

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        youTubeViewModel.refreshPlaylistItems(getString(R.string.playlistId1))
        youTubeViewModel.youTubePlaylistItemsLiveData.observe(viewLifecycleOwner) { response ->
            if (response == null) {
                Toast.makeText(
                    homeActivity,
                    "Unsuccessful network call!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
            binding.apply {
                homeRv1.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                homeRv1.adapter = YouTubeAdapter(homeActivity, response)
            }
        }
        youTubeViewModel.refreshPlaylistItems("PLlEI3XVf9Q8DM2ln0FlcKk0w-uf1Ef5Jg")
        youTubeViewModel.youTubePlaylistItemsLiveData2.observe(viewLifecycleOwner) { response ->
            if (response == null) {
                Toast.makeText(
                    homeActivity,
                    "Unsuccessful network call!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
            binding.apply {
                homeRv2.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                homeRv2.adapter = YouTubeAdapter(homeActivity, response)
            }
        }
        youTubeViewModel.refreshPlaylistItems("PLlEI3XVf9Q8DM2ln0FlcKk0w-uf1Ef5Jg")
        youTubeViewModel.youTubePlaylistItemsLiveData2.observe(viewLifecycleOwner) { response ->
            if (response == null) {
                Toast.makeText(
                    homeActivity,
                    "Unsuccessful network call!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
            binding.apply {
                homeRv2.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                homeRv2.adapter = YouTubeAdapter(homeActivity, response)
            }
        }
        youTubeViewModel.refreshPlaylistItems(HOME_PID_3)
        youTubeViewModel.youTubePlaylistItemsLiveData3.observe(viewLifecycleOwner) { response ->
            if (response == null) {
                Toast.makeText(
                    homeActivity,
                    "Unsuccessful network call!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
            binding.apply {
                homeRv3.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                homeRv3.adapter = YouTubeAdapter(homeActivity, response)
            }
        }

        return root
    }


    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}