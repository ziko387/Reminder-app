package com.example.reminderapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.reminderapp.presentation.components.Splash_Screen
import com.example.reminderapp.presentation.screen.intro.Intro_Screen




@Composable
fun ReminderNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination =Screen.Splash.route ){
        composable(route = Screen.Splash.route){
            Splash_Screen(navController)
        }
        composable(route = Screen.Intro.route){
            Intro_Screen(navController)

        }
    }
}