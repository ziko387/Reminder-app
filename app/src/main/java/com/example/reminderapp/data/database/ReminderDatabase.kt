package com.example.reminderapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.reminderapp.data.model.Reminder
import com.example.reminderapp.data.model.User
import com.example.reminderapp.data.Dao.ReminderDao
import com.example.reminderapp.data.Dao.UserDao
import androidx.room.Room

@Database(entities = [Reminder::class,User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun reminderDao(): ReminderDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase?=null
        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE?: synchronized (this){
               val instance= Room.databaseBuilder(
                   context.applicationContext,
                   AppDatabase::class.java,
                   "Reminder_database"
               ).build()
                INSTANCE=instance
                instance
            }
        }
    }
}




