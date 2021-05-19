package com.example.contactlist.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contactlist.dao.UserDao
import com.example.contactlist.model.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDatabase:RoomDatabase() {

     abstract fun userDao():UserDao
}