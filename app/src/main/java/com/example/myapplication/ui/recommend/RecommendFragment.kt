package com.example.myapplication.ui.recommend

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings.Global
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.HomeActivity
import com.example.myapplication.R
import com.example.myapplication.YoutubeActivity
import com.example.myapplication.databinding.FragmentRecommendBinding
import com.example.myapplication.envs.PLAYLIST_ID_1
import com.example.myapplication.roomdb.db.WeplDatabase
import com.example.myapplication.roomdb.entity.Region
import com.example.myapplication.roomdb.entity.Song
import com.example.myapplication.ui.recyclerview.RecommendAdapter
import com.example.myapplication.youtubeapi.YouTubeViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class RecommendFragment : Fragment() {
    private lateinit var weplDB: WeplDatabase

    // 1. Context를 할당할 변수를 프로퍼티로 선언(어디서든 사용할 수 있게)
    lateinit var homeActivity: HomeActivity
    lateinit var cont: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // 2. Context를 액티비티로 형변환해서 할당
        homeActivity = context as HomeActivity

    }

    // create some variables that we need
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest

    //the permission id is just an int that must be unique so you can use any number
    val PERMISSION_ID = 99

    private var _binding: FragmentRecommendBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this)[RecommendViewModel::class.java]
        val youTubeViewModel: YouTubeViewModel by lazy {
            ViewModelProvider(this)[YouTubeViewModel::class.java]
        }

        _binding = FragmentRecommendBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var loc: TextView = binding.location

        // let's initiate the fused..providerClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(homeActivity)

        getLastLocation(loc)
        // create a new function that return the city name and the country name
        // let's this value add to toast

        //위도 경도 범위가 1번에 해당하면 1에 해당한느 플레이리스트 가져오는 코드
        var myLoc = 1
        if (myLoc == 1) {
            youTubeViewModel.refreshPlaylistItems(PLAYLIST_ID_1)
            youTubeViewModel.youTubePlaylistItemsLiveData.observe(viewLifecycleOwner){response->
                if (response == null) {
                    Toast.makeText(homeActivity, "Unsuccessful netsork call!", Toast.LENGTH_SHORT).show()
                    return@observe
                }
                binding.apply {
                    recommendRecyclerview.layoutManager = LinearLayoutManager(context)
                    recommendRecyclerview.adapter = RecommendAdapter(homeActivity, response)
                }
            }

        }

//        if (true) {
//
//        } else {
//
//        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun playYoutube(videoId:String) {
        var intent = Intent(homeActivity, YoutubeActivity::class.java)
        intent.putExtra("videoId", videoId)
        startActivity(intent)
    }

    // 반경1km내에 존재하는지 확인하는 함수


    // 존재하면 해당하는 지역 정보 가져오기
    private fun getLocationInTheScope(): Region?{
        var regionInfo: Region ?= null
       // db에서 반겨 1km내에 존재하는 관광지 있으면 true(@Query getRegionByLocation()) globalscope으로 db.regiondao실행->해당하는 노래 리스트 가져오기
        GlobalScope.launch(Dispatchers.IO) {

        }
        return regionInfo
    }

    // 지역정보에 해당하는 노래 가져오기
    private fun getSongListByLocation(loc: Int): List<Song>? {
        var songList : List<Song>? = null
        GlobalScope.launch(Dispatchers.IO) {

        }
        return songList
    }

    //a function that will allow us to get the last Location
    private fun getLastLocation(loc: TextView) {
        // first - check permission
        if (checkPermission()) {
            //Now check the location service is enabled
            if (isLocationEnabled()) {
                // let's get the Location
                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        // if the location is null wd will get the new user location
                        // new function
                        // don't forget the add new location
                        getNewLocation()
                    } else {
                        Toast.makeText(
                            homeActivity,
                            "위도 : " + location.latitude + " 경도 : " +
                                    location.longitude + "\n" + "cytiname : " + getCityName(
                                location.latitude,
                                location.longitude
                            ) + " 나라 : " + getCountryName(location.latitude, location.longitude),
                            Toast.LENGTH_LONG
                        ).show()
                        loc.text = getCountryName(location.latitude, location.longitude)
                    }
                }
            }
        } else {
            Toast.makeText(homeActivity, "Please Enable your Location service", Toast.LENGTH_SHORT)
                .show()
            requestPermission()
        }
    }

    //
    private fun getNewLocation() {
        locationRequest.apply {
            LocationRequest()
            priority = Priority.PRIORITY_HIGH_ACCURACY
            interval = 0
//            fastestInterval = 0
            minUpdateDistanceMeters
            numUpdates = 2
        }
        fusedLocationProviderClient!!.requestLocationUpdates(
            locationRequest, locationCallback, Looper.myLooper()
            // let's create the locationCallback vaiable
        )
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            val lastLocation: Location? = p0.lastLocation
            // set the new location
            if (lastLocation != null) {
                Toast.makeText(
                    homeActivity,
                    "위도 : " + lastLocation.latitude + " 경도 : " + lastLocation?.longitude +
                            lastLocation.longitude + "\n" + "cytiname : " + getCityName(
                        lastLocation.latitude,
                        lastLocation.longitude
                    ) + " 나라 : " + getCountryName(lastLocation.latitude, lastLocation.longitude),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    // first, a function that will check the uses permission
    private fun checkPermission(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                homeActivity,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                homeActivity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    // need to create a function that will allow us to get user permission
    private fun requestPermission() {
        // 위치 권한 요구
        ActivityCompat.requestPermissions(
            homeActivity,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            PERMISSION_ID
        )

    }

    // a function that check if the Loction service of the device is enabled
    private fun isLocationEnabled(): Boolean {
        var locationManager =
            homeActivity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    // function to get the city name
    private fun getCityName(lat: Double, long: Double): String {
        var CityName = ""
        var geoCoder = Geocoder(homeActivity, Locale.getDefault())
        var Adress: List<Address> = geoCoder.getFromLocation(lat, long, 1)

        CityName = Adress[0].locality
        return CityName
    }

    // function that return the country name
    private fun getCountryName(lat: Double, long: Double): String {
        var CountryName = ""
        var geoCoder = Geocoder(homeActivity, Locale.getDefault())
        var Adress: List<Address> = geoCoder.getFromLocation(lat, long, 1)

        CountryName = Adress[0].getAddressLine(0)
        return CountryName
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // this is a built in function that check the permission result
        // using it for debigging the code
        if (requestCode == PERMISSION_ID) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("Debug", "You Have the Permission")
            }
        }
    }
}