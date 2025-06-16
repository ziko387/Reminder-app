package com.example.reminderapp.data.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.reminderapp.data.model.Reminder
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import androidx.room.Delete
import androidx.room.Room


@Dao
interface ReminderDao {
    @Insert
    suspend fun insertReminder(reminder: Reminder)

    @Delete
    suspend fun deleteReminder(reminder: Reminder)

    @Update
  suspend fun updateReminder(reminder: Reminder)
    @Query("SELECT * FROM reminder WHERE userId = :userId AND isCompleted=0 ORDER BY datetime ASC")
    fun getUpcomingReminders(userId: Int): Flow<List<Reminder>>
    @Query("SELECT * FROM reminder WHERE userId = :userId AND isCompleted=0 ORDER BY datetime ASC")
    fun getCompletedReminders(userId: Int): Flow<List<Reminder>>
    @Query("SELECT * FROM reminder WHERE datetime BETWEEN :startDate AND :endDate")
    suspend fun getRemaindersBetweenDates(startDate: Long, endDate: Long): List<Reminder>




}


