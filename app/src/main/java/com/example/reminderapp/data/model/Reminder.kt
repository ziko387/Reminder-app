package com.example.reminderapp.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.ZoneOffset

@Entity(tableName = "reminder")
data class Reminder(
    @PrimaryKey(autoGenerate = true) val reminderId: Int=0,
    val firebaseId: String="",
    val userId: Int=0,
    val name: String="",
    val description: String="",
    val datetime: Long= LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli(),
    val isCompleted: Boolean=false

    )

