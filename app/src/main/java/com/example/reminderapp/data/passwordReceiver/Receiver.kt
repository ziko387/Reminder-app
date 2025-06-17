package com.example.reminderapp.data.passwordReceiver

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


object Receiver {
    suspend fun sendPasswordResetRequest(email: String): Result<Unit> {
        return try {
            Firebase.auth.sendPasswordResetEmail(email).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)

        }
    }
}