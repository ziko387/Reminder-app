package com.example.reminderapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.reminderapp.presentation.components.Splash_Screen
import com.example.reminderapp.presentation.screen.intro.Intro_Screen
import com.example.reminderapp.presentation.screen.intro.Intro_Screen2
import com.example.reminderapp.presentation.screen.intro.Intro_Screen3
import com.example.reminderapp.presentation.navigation.Screen
import com.example.reminderapp.presentation.components.LoginScreen
import com.example.reminderapp.presentation.components.RegisterUser


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
        composable("intro2"){
            Intro_Screen2(navController)
        }
        composable("intro3"){
            Intro_Screen3(navController)
        }
       composable("Register"){
           RegisterUser(navController)
       }
       composable("Login"){
           LoginScreen(navController)
       }
       }
    }
