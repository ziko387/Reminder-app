package com.example.reminderapp.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val userid: Int,
    val username: String,
    val email: String,
    val password: String
)
@Entity(tableName = "reminder")
data class Reminder(
    @PrimaryKey(autoGenerate = true) val reminderid: Int,
    val userid: Int,
    val name: String,
    val description: String,
    val datetime: LocalDateTime,
    val isCompleted: Boolean=false

    )

