package com.example.reminderapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import  androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.reminderapp.data.passwordReceiver.ForgotPasswordViewmodel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.reminderapp.R
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.ButtonDefaults


@Composable
fun ForgotPasswordScreen(navController: NavController, showForgotPasswordScreen: Boolean,
          onResetSent: () -> Unit = {},
           viewmodel: ForgotPasswordViewmodel= viewModel()) {
    var email by remember { mutableStateOf("") }
    val isLoading by viewmodel.isLoading.collectAsState()
    val error by viewmodel.error.collectAsState()
    val isSuccess by viewmodel.isSuccess.collectAsState()
    val showForgotPasswordScreen by remember { mutableStateOf(true) }

    if (isSuccess) {
        onResetSent()
        navController.navigate("Login")
        {
            popUpTo("Login") {
                inclusive = true
                saveState = true
            }
            launchSingleTop = true
        }



    }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Forgot Password",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(6.dp))
        Image(
            painter = painterResource(id = R.drawable.forgot),
            contentDescription = "logo",
            modifier = Modifier.size(119.dp).clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { viewmodel.sendPasswordResetEmail(email) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            enabled = !isLoading && email.isNotBlank(),
            shape = MaterialTheme.shapes.medium
        ) {
            if (isLoading) {
                Text(text = "Loading...")
            } else {
                Text(text = "Reset Password")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (error != null) {
            Text(
                text = error!!,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.fillMaxWidth()
            )
        }
        if (isSuccess) {

            Text(
                text = "Reset Password",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

    }
}


    @Preview(showBackground = true)
    @Composable
    fun ForgotPasswordScreenPreview() {
        ForgotPasswordScreen(
            rememberNavController(), showForgotPasswordScreen = true
        )
    }
