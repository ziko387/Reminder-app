package com.example.reminderapp.presentation.navigation



sealed class Screen(val route : String){
    object Splash : Screen("Splash_Screen")
    object Intro: Screen("Intro_Screen")
}