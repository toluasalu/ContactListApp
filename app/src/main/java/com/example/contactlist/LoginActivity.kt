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
import com.example.contactlist.databinding.ActivityLoginBinding
import java.util.concurrent.Executors

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var db: AppDatabase
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Connect to db
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "appDatabase.db"
        ).build()

        userDao = db.userDao()

        binding.loginBtn.setOnClickListener {

            validateUser()
        }


    }

    private fun validateUser() {
          val enteredUserEmail = binding.emailEditText.text.toString()
          val enteredUserPassword = binding.passwordEditText.text.toString()


        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        executor.execute {
            val data = userDao.findUser(enteredUserEmail,enteredUserPassword)




            handler.post {
                Toast.makeText(applicationContext,"Login Successful", Toast.LENGTH_SHORT).show()
                val contactIntent = Intent(applicationContext,MainActivity::class.java)
                startActivity(contactIntent)
            }


        }


    }
}