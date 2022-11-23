package com.example.myapplication.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.databinding.ActivitySignupBinding
import com.example.myapplication.envs.TAG_D
import com.example.myapplication.roomdb.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignupViewModel(): ViewModel() {

//    fun writeData() {
//        val firstName = binding.firstName.text.toString()
//        val lastName = binding.lastName.text.toString()
//        val email = binding.email.text.toString()
//        val pwd = binding.email.text.toString()
//        val pwdCheck = binding.pwdCheck.text.toString()
//
//        if (firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && pwd.isNotEmpty() && pwdCheck.isNotEmpty()) {
//            val user = User(null, firstName, lastName, email, pwd)
//            GlobalScope.launch(Dispatchers.IO){
//                weplDB.userDao().insert(user)
//            }
//            binding.firstName.text.clear()
//            binding.lastName.text.clear()
//            binding.email.text.clear()
//            binding.pwd.text.clear()
//            binding.pwdCheck.text.clear()
//
//            Toast.makeText(this@SignupActivity, "welcome $firstName 🎉", Toast.LENGTH_SHORT).show()
//        }
//    }

//    private suspend fun displayData(user: User) {
//        withContext(Dispatchers.Main){
//            binding.tvFirstName.text = user.firstName
//            binding.tvLastName.text = user.lastName
//            binding.tvEmail.text = user.email
//        }
//    }
//
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

//    fun checkPwd(): Boolean {
//        val pwd = binding.pwd.text.toString()
//        val pwdCheck = binding.pwdCheck.text.toString()
//        if (pwd.equals(pwdCheck)) {
//            return true
//        }
//        return false
//    }
//
//    fun isAlreadyJoined(): Boolean {
//        val firstName = binding.firstName.text.toString()
//        val lastName = binding.lastName.text.toString()
//        val email = binding.email.text.toString()
//        val pwd = binding.email.text.toString()
//        val pwdCheck = binding.pwdCheck.text.toString()
//
//        var returnedEmail : String?= null
//
//        if (firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && pwd.isNotEmpty() && pwdCheck.isNotEmpty()) {
//            GlobalScope.launch(Dispatchers.IO){
//                returnedEmail = weplDB.userDao().isAlreadyJoined(email)
//            }
//        }
//        Log.d("returnedEmail", returnedEmail.toString())
//        if (returnedEmail != null) {
//            return true
//        }
//        return false
//    }
}