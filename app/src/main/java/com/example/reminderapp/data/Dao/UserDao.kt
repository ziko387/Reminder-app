package com.example.reminderapp.data.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.reminderapp.data.model.Reminder
import com.example.reminderapp.data.model.User
import java.util.EnumMap

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)
    @Query("SELECT * FROM user WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?

}



