package com.example.reminderapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.reminderapp.data.model.Reminder
import com.example.reminderapp.data.Dao.ReminderDao
import androidx.room.Room
import com.example.reminderapp.Converter
import androidx.room.TypeConverters

@Database(entities = [Reminder::class,], version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun reminderDao(): ReminderDao
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




