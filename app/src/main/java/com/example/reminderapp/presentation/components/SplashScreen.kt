package com.example.reminderapp.presentation.components

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun Splash_Screen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(Scree)
    }
}