package com.example.contactlist.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.contactlist.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user_information")
    fun getAll(): List<User>

    @Query("SELECT * FROM user_information WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user_information WHERE email LIKE :emailAddress AND " +
            "password LIKE :userPassword LIMIT 1")
    fun findUser(emailAddress: String, userPassword: String): User

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}