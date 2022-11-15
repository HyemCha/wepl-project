package com.example.myapplication

import android.app.appsearch.GlobalSearchSession
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.databinding.ActivityHomeBinding
import com.example.myapplication.databinding.ActivitySignupBinding
import com.example.myapplication.roomdb.db.WeplDatabase
import com.example.myapplication.roomdb.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignupActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignupBinding
    private lateinit var weplDB : WeplDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_signup)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        weplDB = WeplDatabase.getDatabase(this)

        binding.button1.setOnClickListener{
            if(isAlreadyJoined()) {
                Toast.makeText(this, "이미 가입하셨네요!", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this@SignupActivity, HomeActivity::class.java)
//                startActivity(intent)
            }
            else if (checkPwd()) {
                Toast.makeText(this, "아ㅓ니", Toast.LENGTH_SHORT).show()
                writeData()
            }else {
                Toast.makeText(this, "비밀번호가 일지하지 않습니다👾", Toast.LENGTH_SHORT).show()
            }
        }

        binding.button2.setOnClickListener{
            readData()
        }
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

            Toast.makeText(this@SignupActivity, "welcome " + firstName, Toast.LENGTH_SHORT).show()
        }
    }

    private suspend fun displayData(user: User) {
        withContext(Dispatchers.Main){
            binding.tvFirstName.text = user.firstName
            binding.tvLastName.text = user.lastName
            binding.tvEmail.text = user.email
        }
    }

    private fun readData() {
        val firstName = binding.firstName.text.toString()

        if (firstName.isNotEmpty()) {
            lateinit var user: User

            GlobalScope.launch {
                user = weplDB.userDao().findByFirstName(firstName)
                displayData(user)
            }
        }
    }

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