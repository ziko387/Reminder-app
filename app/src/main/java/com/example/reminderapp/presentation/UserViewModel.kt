package com.example.reminderapp.presentation
import com.example.reminderapp.data.Repository.UserRepository
import androidx.lifecycle.ViewModel
import com.example.reminderapp.data.model.User

class UserViewModel (private val userRepository: UserRepository): ViewModel() {
    private var _currentUser: User? = null
    val currentUser: User?
        get() = _currentUser

    suspend fun login(email: String, password: String): User? {
        return try {
            val user = userRepository.login(email, password)
                ?: return null
            _currentUser = user
            user
            } catch (e: Exception) {
            null

        }

    }
    suspend fun register(username: String, email: String, password: String): User? {

        return try {
            val user = userRepository.register(username, email, password)
                ?: return null
            _currentUser = user
            user
        } catch (e: Exception) {
            null
        }
        }
    fun logout() {
        userRepository.logout()
        _currentUser = null

    }

}