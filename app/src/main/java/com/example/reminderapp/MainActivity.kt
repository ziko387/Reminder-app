package com.example.reminderapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import com.example.reminderapp.ui.theme.ReminderAppTheme
import androidx.navigation.compose.rememberNavController
import com.example.reminderapp.presentation.navigation.ReminderNavGraph
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.firebase.Firebase
import com.google.firebase.initialize



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        Firebase.initialize(this)
        setContent {
            ReminderAppTheme {
                val navController= rememberNavController()
                ReminderNavGraph(navController = navController)
            }

        }
    }

    override fun onStart() {
        super.onStart()
        //
    }
}


