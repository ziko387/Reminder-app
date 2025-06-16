package com.example.reminderapp.data.Repository


import com.example.reminderapp.data.Dao.ReminderDao
import com.example.reminderapp.data.model.Reminder
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import java.time.ZoneOffset

class ReminderRepository(private val reminderDao: ReminderDao) {
  fun getUpcomingReminders(userId: Int,):Flow<List<Reminder>> {
        val currentTime = LocalDateTime.now().toString()
        return reminderDao.getUpcomingReminders(userId = 0)
    }
     fun getCompletedReminders(userId: Int): Flow<List<Reminder>> {
        return reminderDao.getCompletedReminders(0)
    }
    suspend fun addRemainder(reminder: Reminder){
        return reminderDao.insertReminder(reminder)
        return try {
          reminderDao.insertReminder(reminder)
        } catch (e: Exception) {
            println("Error adding reminder: ${e.message}")
        }
    }
    suspend fun updateReminder(reminder: Reminder){
        return reminderDao.updateReminder(reminder)
    }
    suspend fun deleteReminder(reminder: Reminder){
        return reminderDao.deleteReminder(reminder)
        return try {
            reminderDao.deleteReminder(reminder)
        } catch (e: Exception) {
            println("Error deleting reminder: ${e.message}")
        }
    }
    suspend fun getRemaindersBetweenDates(startDate: LocalDateTime, endDate: LocalDateTime): List<Reminder> {
        val startTimestamp = startDate.toInstant(ZoneOffset.UTC).toEpochMilli()
        val endTimestamp = endDate.toInstant(ZoneOffset.UTC).toEpochMilli()
        return reminderDao.getRemaindersBetweenDates(startTimestamp, endTimestamp)
    }




}

