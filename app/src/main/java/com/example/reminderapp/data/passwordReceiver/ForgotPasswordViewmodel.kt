package com.example.reminderapp.data.passwordReceiver

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope

class ForgotPasswordViewmodel : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _isSuccess= MutableStateFlow(false)
    val isSuccess: StateFlow<Boolean> = _isSuccess

    fun sendPasswordResetEmail(email: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val result = Receiver.sendPasswordResetRequest(email)
                if (result.isSuccess) {
                    _isSuccess.value = true
                } else {
                    _error.value = result.exceptionOrNull()?.message ?: "Failed to send the email"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"

            } finally {
                _isLoading.value = false
            }
        }
    }

}