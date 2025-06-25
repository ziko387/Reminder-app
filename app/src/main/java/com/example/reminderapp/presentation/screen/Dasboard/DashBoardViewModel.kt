package com.example.reminderapp.presentation.screen.Dasboard
import androidx.lifecycle.ViewModel
import com.example.reminderapp.data.Repository.ReminderRepository
import com.example.reminderapp.data.Repository.ReminderRepositoryImpl
import com.example.reminderapp.data.model.Reminder
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharingStarted
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider


class DashBoardViewModel(private val reminderRepository: ReminderRepository = ReminderRepositoryImpl()) : ViewModel(){
 val firebaseReminders: StateFlow<List<Reminder>> = reminderRepository.fetchRemaindersFromFirebase().stateIn(
     scope = viewModelScope,
     started = SharingStarted.WhileSubscribed(5000),
     initialValue = emptyList()
 )
    suspend fun updateRemainder(reminder: Reminder){
        reminderRepository.updateReminderToFirebase(reminder)
    }
      fun deleteRemainder(reminder: Reminder) {
        reminderRepository.deleteReminderToFirebase(reminder)
    }
    fun addRemainder(
        name: String,
        description: String,
        datetime: Long,
        isCompleted: Boolean
    ){
        viewModelScope.launch {
            val newRemainder= Reminder(
                userId = 0,
                name = name,
                description = description,
                datetime = datetime,
                isCompleted = isCompleted
            )
            reminderRepository.addRemainderToFirebase(newRemainder)
        }

    }

}
class DashBoardViewModelFactory(private val repository: ReminderRepository) :
    ViewModelProvider.Factory {
    override fun<T : ViewModel> create(modelClass: Class<T>) : T {
        if(modelClass.isAssignableFrom(ReminderRepository::class.java)){
            @Suppress("UNCHECKED_CAST")
            return DashBoardViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
