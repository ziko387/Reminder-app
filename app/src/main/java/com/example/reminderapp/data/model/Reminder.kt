package com.example.reminderapp.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "reminder")
data class Reminder(
    @PrimaryKey(autoGenerate = true) val reminderId: Int=0,
    val userId: Int=0,
    val name: String="",
    val description: String="",
    val datetime: LocalDateTime=LocalDateTime.now(),
    val isCompleted: Boolean=false

    )

