package com.example.reminderapp.data.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.reminderapp.data.model.Reminder
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {
    @Insert
    suspend fun insertReminder(reminder: Reminder)

    @Update
    suspend fun updateReminder(reminder: Reminder)
    @Query("SELECT * FROM reminder WHERE userid = :userid AND isCompleted=0 ORDER BY datetime ASC")
    suspend fun getUpcomingReminders(userid: Int): Flow<List<Reminder>>
    @Query("SELECT * FROM reminder WHERE userid = :userid AND isCompleted=1 ORDER BY datetime ASC")
    suspend fun getCompletedReminders(userid: Int): Flow<List<Reminder>>
    suspend fun deleteReminder(reminder: Reminder)
    suspend fun getRemaindersBetweenDates(startDate: Long, endDate: Long): List<Reminder>
}

