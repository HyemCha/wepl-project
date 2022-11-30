package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.databinding.FragmentRecommendBinding
import com.example.myapplication.envs.TAG_D
import com.example.myapplication.roomdb.db.WeplDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class LoginActivity : Fragment() {

    // 1. Context를 할당할 변수를 프로퍼티로 선언(어디서든 사용할 수 있게)
    lateinit var homeActivity: HomeActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // 2. Context를 액티비티로 형변환해서 할당
        homeActivity = context as HomeActivity

    }


    private lateinit var weplDB: WeplDatabase
    private lateinit var userName: TextView
    private var _binding: ActivityLoginBinding? =null
    private val binding get() = _binding!!

//    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
//        super.onCreate(savedInstanceState, persistentState)
//        binding = ActivityLoginBinding.inflate(layoutInflater)
//        setContentView(binding.root)



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ActivityLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.login.setOnClickListener {
            val email = binding.email!!.text.toString()
            val pwd = binding.password!!.text.toString()
            weplDB = WeplDatabase.getDatabase(homeActivity)
            var name :String? = null
            GlobalScope.launch {
                name = weplDB.userDao().isAlreadyJoined(email)
                Log.d(TAG_D, "namenamename$name")
                if (name != null){
                    binding.name!!.text = name
                    binding.name!!.visibility = View.VISIBLE
                    binding.userIC!!.visibility = View.VISIBLE
                    binding.login!!.visibility = View.GONE
                    binding.email!!.visibility = View.GONE
                    binding.password!!.visibility = View.GONE
                }else{
//                    Toast.makeText(homeActivity, "회원정보가 없습니다.", Toast.LENGTH_SHORT).show()
                    Log.d(TAG_D, "namenull$name")
                }
            }
//            userLogin(email, pwd, this.id)
        }
        return root
    }

    fun userLogin(email: String, pwd: String, id:Int) {

        weplDB = WeplDatabase.getDatabase(context as HomeActivity)
        GlobalScope.launch(Dispatchers.IO) {
            var name = weplDB.userDao().isAlreadyJoined(email)
            Log.d(TAG_D, "namenamename$name")
            if (name != null){
                binding.name!!.text = name
                binding.name!!.visibility = View.VISIBLE
                binding.userIC!!.visibility = View.VISIBLE
                binding.login!!.visibility = View.GONE
                binding.email!!.visibility = View.GONE
                binding.password!!.visibility = View.GONE
            }else{
                Toast.makeText(homeActivity, "회원정보가 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}