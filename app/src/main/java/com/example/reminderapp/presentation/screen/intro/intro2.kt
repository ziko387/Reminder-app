package com.example.reminderapp.presentation.screen.intro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.reminderapp.R

@Composable
fun Intro_Screen2(navController: NavController){
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            Image(
                painter = painterResource(id= R.drawable.exam)
                , contentDescription = "splash logo.",
                modifier = Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.height(65.dp))
            Text(
                text = "get to plan on your events",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                navController.navigate("intro3")
            }) {
                Text(
                    text = "next",
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
        }


    }
}
@Preview(showBackground = true)
@Composable
fun Intro_Screen2Preview(){
    Intro_Screen2(rememberNavController())
}