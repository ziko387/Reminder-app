package com.example.reminderapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import com.example.reminderapp.R
import androidx.compose.foundation.layout.size
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.material3.Button
import androidx.compose.material3.TextButton
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.material3.IconButton
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock


import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.text.input.VisualTransformation


@Composable
fun LoginScreen() {
    var passwordVisible by remember { mutableStateOf(false) }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Column(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Login ",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 30.sp
        )
        Image(
            painter = painterResource(id = R.drawable.reminder1), contentDescription = "logo",

            modifier = Modifier.size(119.dp).clip(CircleShape), contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(25.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Username") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                focusedLabelColor = Color.White,
               unfocusedLabelColor = Color.White

            ),

//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                focusedBorderColor = Color.White,
//                unfocusedBorderColor = Color.White,
//                textColor = Color.White,
//                focusedLabelColor = Color.White,
//                unfocusedLabelColor = Color.White

//            ),
            singleLine = true,
            visualTransformation =if(passwordVisible)
                VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Default.Lock

                else Icons.Default.Lock


                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = if(passwordVisible)
                        "Hide Password" else "Show Password",
                        tint = Color.White)

                }
            }

        )
        Spacer(Modifier.height(25.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White
            ),

            singleLine = true,
            visualTransformation =if(passwordVisible)
                VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Default.Lock

                else Icons.Default.Lock


                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = if(passwordVisible)
                        "Hide Password" else "Show Password",
                        tint = Color.White)

                }
            }

        )
        Spacer(Modifier.height(25.dp))

        Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
           Text(
               text = "Login",
               style = MaterialTheme.typography.bodyMedium,
               fontSize = 20.sp
           )

        }

        TextButton(onClick = {  }) {
            Text(
                text = "new member?, register",
                style = MaterialTheme.typography.bodyMedium)

        }

    }
}



@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}