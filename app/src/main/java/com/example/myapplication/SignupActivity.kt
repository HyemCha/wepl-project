package com.example.myapplication

import android.app.appsearch.GlobalSearchSession
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityHomeBinding
import com.example.myapplication.databinding.ActivitySignupBinding
import com.example.myapplication.envs.TAG_D
import com.example.myapplication.roomdb.db.WeplDatabase
import com.example.myapplication.roomdb.entity.User
import com.example.myapplication.ui.notifications.NotificationsFragment
import com.example.myapplication.viewmodel.SignupViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignupActivity : Fragment() {

    // 1. Context를 할당할 변수를 프로퍼티로 선언(어디서든 사용할 수 있게)
    lateinit var homeActivity: HomeActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // 2. Context를 액티비티로 형변환해서 할당
        homeActivity = context as HomeActivity

    }

//    private lateinit var binding : ActivitySignupBinding
    private lateinit var binding : ActivitySignupBinding
    private lateinit var weplDB : WeplDatabase

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
        val signupViewModel = ViewModelProvider(this)[SignupViewModel::class.java]

//        binding = ActivitySignupBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        binding = ActivitySignupBinding.inflate(inflater, container, false)

        weplDB = WeplDatabase.getDatabase(homeActivity)

        binding.button1.setOnClickListener{
            if(isAlreadyJoined()) {
                Toast.makeText(homeActivity, "이미 가입하셨네요!", Toast.LENGTH_SHORT).show()
            }
            else if (checkPwd()) {
                writeData()
            }else {
                Toast.makeText(homeActivity, "비밀번호가 일지하지 않습니다👾", Toast.LENGTH_SHORT).show()
            }
        }

        binding.before.setOnClickListener{
            homeActivity.supportFragmentManager.beginTransaction().replace(this.id, NotificationsFragment()).commitAllowingStateLoss()
        }

        return binding.root
    }
    private fun writeData() {
        val firstName = binding.firstName.text.toString()
        val lastName = binding.lastName.text.toString()
        val email = binding.email.text.toString()
        val pwd = binding.email.text.toString()
        val pwdCheck = binding.pwdCheck.text.toString()

        if (firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && pwd.isNotEmpty() && pwdCheck.isNotEmpty()) {
            val user = User(null, firstName, lastName, email, pwd)
            GlobalScope.launch(Dispatchers.IO){
                weplDB.userDao().insert(user)
            }
            binding.firstName.text.clear()
            binding.lastName.text.clear()
            binding.email.text.clear()
            binding.pwd.text.clear()
            binding.pwdCheck.text.clear()

            Toast.makeText(homeActivity, "welcome $firstName 🎉", Toast.LENGTH_SHORT).show()
        }
    }

//    private suspend fun displayData(user: User) {
//        withContext(Dispatchers.Main){
//            binding.tvFirstName.text = user.firstName
//            binding.tvLastName.text = user.lastName
//            binding.tvEmail.text = user.email
//        }
//    }

//    private fun readData() {
//        val searchName = binding.searchName.text.toString()
//
//        if (searchName.isNotEmpty()) {
//            lateinit var user: User
//
//            GlobalScope.launch {
//                user = weplDB.userDao().findByFirstName(searchName)
//                Log.d(TAG_D, "$user")
//                displayData(user)
//            }
//        }
//    }

    private fun checkPwd(): Boolean {
        val pwd = binding.pwd.text.toString()
        val pwdCheck = binding.pwdCheck.text.toString()
        if (pwd.equals(pwdCheck)) {
            return true
        }
        return false
    }

    private fun isAlreadyJoined(): Boolean {
        val firstName = binding.firstName.text.toString()
        val lastName = binding.lastName.text.toString()
        val email = binding.email.text.toString()
        val pwd = binding.email.text.toString()
        val pwdCheck = binding.pwdCheck.text.toString()

        var returnedEmail : String?= null

        if (firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && pwd.isNotEmpty() && pwdCheck.isNotEmpty()) {
            GlobalScope.launch(Dispatchers.IO){
                returnedEmail = weplDB.userDao().isAlreadyJoined(email)
            }
        }
        Log.d("returnedEmail", returnedEmail.toString())
        if (returnedEmail != null) {
            return true
        }
        return false
    }
}