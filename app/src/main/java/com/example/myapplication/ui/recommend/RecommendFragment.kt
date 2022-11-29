package com.example.myapplication.ui.recommend

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.HomeActivity
import com.example.myapplication.databinding.FragmentRecommendBinding
import com.example.myapplication.envs.*
import com.example.myapplication.roomdb.db.WeplDatabase
import com.example.myapplication.ui.recyclerview.RecommendAdapter
import com.example.myapplication.youtubeapi.YouTubeViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import java.util.*
import kotlin.math.floor
import kotlin.math.roundToInt

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

    var lng: Double? = null
    var lat: Double? = null

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest

    val PERMISSION_ID = 99

    private var _binding: FragmentRecommendBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val recommendViewModel =
            ViewModelProvider(this)[RecommendViewModel::class.java]
        val youTubeViewModel: YouTubeViewModel by lazy {
            ViewModelProvider(this)[YouTubeViewModel::class.java]
        }

        _binding = FragmentRecommendBinding.inflate(inflater, container, false)
        val root: View = binding.root



        var loc: TextView = binding.location
        var pId: String = ""

//        val mapView = MapView(homeActivity)
//        binding.clKakaoMapView.addView(mapView)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(homeActivity)

        getLastLocation(loc, recommendViewModel)



        recommendViewModel.regionWithin1km.observe(viewLifecycleOwner) {
            if (it == null) {
                Toast.makeText(homeActivity, "There is nothing within 1km", Toast.LENGTH_SHORT)
                    .show()
                return@observe
            }
            Log.d(TAG_D, "rec-viewmodel$it")
            binding.location.text = "${it.location} 근처"

        }

        recommendViewModel.pId.observe(viewLifecycleOwner) {
            pId = it
            Log.d(TAG_D, "pIdAfterPIdsetting1$pId")

            youTubeViewModel.refreshPlaylistItems(pId)
        }

        recommendViewModel.distance.observe(viewLifecycleOwner){
            var d = floor(it*1000).roundToInt()
            binding.distance.text = "내 위치로부터 약 ${d}m 떨어짐"
        }


        recommendViewModel.tags.observe(viewLifecycleOwner){
            if (it == null) {
                return@observe
            }
            binding.tag1.text = it[0]
            binding.tag2.text = it[1]
            binding.tag3.text = it[2]
            binding.tag4.text = it[3]
            binding.tag5.text = it[4]
        }


        youTubeViewModel.youTubePlaylistItemsLiveData.observe(viewLifecycleOwner) { response ->
            if (response == null) {
                Toast.makeText(homeActivity, "Unsuccessful network call!", Toast.LENGTH_SHORT)
                    .show()
                return@observe
            }
            binding.apply {
                recommendRecyclerview.layoutManager = LinearLayoutManager(context)
                recommendRecyclerview.adapter = RecommendAdapter(homeActivity, response)
            }
        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //a function that will allow us to get the last Location
    fun getLastLocation(loc: TextView, recViewModel: RecommendViewModel) {
        // first - check permission
        if (checkPermission()) {
            //Now check the location service is enabled
            if (isLocationEnabled()) {
                // let's get the Location
                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        getNewLocation()
                    } else {
//                        loc.text = getCountryName(location.latitude, location.longitude)
                        recViewModel.getTheRegionInTheScope(
                            homeActivity,
                            location.longitude,
                            location.latitude
                        )
                        lng = location.longitude
                        lat = location.latitude
                    }
                }
            }
        } else {
//            Toast.makeText(homeActivity, "Please Enable your Location service", Toast.LENGTH_SHORT)
//                .show()
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
                            lastLocation.longitude + "\n" + "cityname : " + getCityName(
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

    fun initLonLat(lon: Double, lat: Double) {

    }

//    override fun onMapReady(p0: GoogleMap) {
//        googleMap = p0
//        googleMap!!.mapType = GoogleMap.MAP_TYPE_NORMAL
//        Log.d(TAG_D, "lnglat onMapReady$lng")
//    }
}