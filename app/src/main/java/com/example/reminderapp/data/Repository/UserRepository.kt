package com.example.reminderapp.data.Repository

import android.content.res.Resources
import com.example.reminderapp.data.model.User
import com.example.reminderapp.data.Dao.UserDao


class UserRepository(private val userDao: UserDao) {

    private var _currentUser: User? = null
    val currentUser: User?
        get() = _currentUser

    suspend fun login(email: String): User? {
        return try {
            val user = userDao.getUserByEmail(email)


            _currentUser = user
            user
        } catch (e: Exception) {
            null
        }
    }

    suspend fun register( email: String,): User? {
       return try {
           if (userDao.getUserByEmail(email) != null) {
               return null
           }
           val user=
               User( email = email )

           userDao.insertUser(user)
           _currentUser = user
           user
       } catch (e: Exception) {
           null
       }
    }
    fun logout() {
        _currentUser = null
    }
}



