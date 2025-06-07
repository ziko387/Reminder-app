package com.example.reminderapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.reminderapp.ui.theme.ReminderAppTheme
import androidx.navigation.compose.rememberNavController
import com.example.reminderapp.presentation.navigation.ReminderNavGraph


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReminderAppTheme {
                val navController= rememberNavController()
                ReminderNavGraph(navController = navController)
            }

        }
    }
}


