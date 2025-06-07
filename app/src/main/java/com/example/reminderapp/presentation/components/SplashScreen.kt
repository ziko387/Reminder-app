package com.example.reminderapp.presentation.components

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import com.example.reminderapp.presentation.navigation.Screen
import com.example.reminderapp.R
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController



@Composable
fun Splash_Screen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(Screen.Intro.route)
        {
            popUpTo(Screen.Splash.route) {
                {inclusive = true}
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            Image(
                painter = painterResource(id= R.drawable.calender)
                , contentDescription = "splash logo.",
                modifier = Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Reminder App",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Splash_ScreenPreview(){
    Splash_Screen(navController = NavController(context = LocalContext.current))
}