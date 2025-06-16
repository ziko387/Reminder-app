package com.example.reminderapp.presentation.screen.Dasboard
import com.example.reminderapp.data.Repository.ReminderRepository
import com.example.reminderapp.data.model.Reminder
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavViewModelStoreProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import java.util.Date
import com.example.reminderapp.data.Dao.ReminderDao
import com.google.common.primitives.UnsignedInts.remainder

class DashBoardViewModel(
    private val navController: NavController,
    private val reminderDao: ReminderDao

): ViewModel()

{
    private val reminderRepository = ReminderRepository(
        reminderDao = TODO()
    )


    fun addRemainder(
        userId: Int,
        name: String,
        description: String,
        datetime: LocalDateTime,
        isCompleted: Boolean
    ){
        val reminder = Reminder(
            userId = userId,
            name = name,
            description = description,
            datetime = datetime,
            isCompleted = isCompleted)



        runBlocking {
            reminderRepository.addRemainder(reminder)
        }

    }
    fun getUpcomingReminders(userId: Int): Flow<List<Reminder>>{
        return reminderRepository.getUpcomingReminders(userId)

    }
    fun getCompletedReminders(userId: Int): Flow<List<Reminder>> {
        return reminderRepository.getCompletedReminders(userId)
    }


    fun updateReminder(
        userId: Int,
        name: String,
        description: String,
        datetime: LocalDateTime,
    ){
        val reminder = Reminder(
            userId = userId,
            name = name,
            description = description,
            datetime = datetime,)
    }

}