package com.example.reminderapp.presentation

import com.example.reminderapp.data.Repository.ReminderRepository
import com.example.reminderapp.data.model.Reminder
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import java.util.Date


class ReminderViewModel(private val reminderRepository: ReminderRepository, ) : ViewModel() {
    val getUpcomingReminders: (Reminder) -> Unit = { remainder ->
      reminderRepository.getUpcomingReminders(remainder.userId)
    }
    val getCompletedReminders: (Reminder) -> Unit = { remainder ->
        reminderRepository.getCompletedReminders(remainder.userId)

        val addRemainder: (Reminder) -> Unit = { remainder ->
            runBlocking {
                reminderRepository.addRemainder(remainder)
            }
        }
        val updateReminder: (Reminder) -> Unit = { remainder ->
            runBlocking {
                reminderRepository.updateReminder(remainder)
            }
        }
        val deleteReminder: (Reminder) -> Unit = { remainder ->
            runBlocking {
                reminderRepository.deleteReminder(remainder)
            }
        }
        val getRemaindersBetweenDates: (LocalDateTime, LocalDateTime) -> Unit =
            { startDate, endDate ->
                runBlocking {
                    reminderRepository.getRemaindersBetweenDates(startDate, endDate)
                }

            }
    }
}