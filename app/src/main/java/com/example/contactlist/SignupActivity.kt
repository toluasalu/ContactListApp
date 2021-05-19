package com.example.contactlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.room.Room
import com.example.contactlist.dao.UserDao
import com.example.contactlist.database.AppDatabase
import com.example.contactlist.databinding.ActivitySignupBinding
import com.example.contactlist.model.User
import java.util.concurrent.Executors

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var db: AppDatabase
    private lateinit var userDao: UserDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Connect to db
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "appDatabase.db"
        ).build()

        userDao = db.userDao()

        binding.signupButton.setOnClickListener { createUser() }

    }

    private fun createUser() {
        val userName = binding.nameInputEditText.text.toString()
        val userEmail = binding.emailInputEditText.text.toString()
        val userPassword = binding.passwordInputEditText.text.toString()

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        executor.execute {
            userDao.insertAll(User(0, userName, userEmail, userPassword))


            handler.post {
                Toast.makeText(
                    applicationContext,
                    "New User was created succesfully",
                    Toast.LENGTH_SHORT
                ).show()

                val loginIntent = Intent(applicationContext,LoginActivity::class.java)
                startActivity(loginIntent)
            }


        }


    }
}