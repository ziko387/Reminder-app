package com.example.reminderapp.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Colors
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextButton
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.platform.LocalContext
import com.example.reminderapp.presentation.components.RegisterUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.content.Context



@Composable
fun RegisterUser(
navController: NavController,
onSignupClick: (String, String) -> Unit
) {

    var error by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().background(Color.Black).padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Register",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h4,
            fontSize = 30.sp
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                textColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White

            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(25.dp))


        Spacer(Modifier.height(25.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                trailingIconColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                textColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White


            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible)
                VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Default.Lock
                else Icons.Default.Lock

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = image, contentDescription = if (passwordVisible)
                            "Hide Password" else "Show Password",
                        tint = Color.White
                    )

                }
            }
        )
        Spacer(Modifier.height(25.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("confirmPassword") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                textColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible)
                VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Default.Lock
                else Icons.Default.Lock

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = image, contentDescription = if (passwordVisible)
                            "Hide Password" else "Show Password",
                        tint = Color.White
                    )

                }
            }
        )
        Spacer(Modifier.height(25.dp))

        Button(
            onClick = {
                if (password != confirmPassword) {
                    error = "password does not match!!"
                } else {
                    registerUser(
                        email, password,
                        context, navController,
                            onError = { errorMsg -> error = errorMsg })
                        }

                },
                modifier = Modifier.fillMaxWidth()
                ) {
                Text(
                    text = "Register",
                    style = MaterialTheme.typography.button,
                    fontSize = 20.sp,
                )

            }
                TextButton(onClick = {
                    navController.navigate("Login") {
                        popUpTo("Login") {
                            inclusive = true
                            saveState = true
                        }
                        launchSingleTop = true
                    }
                })
                {
                    Text(
                        text = "Already have an account? Login",
                        style = MaterialTheme.typography.body1
                    )
                }


            }


    }
fun registerUser(email: String, password: String,
                 context: Context, navController: NavController,
                 onError: (String)-> Unit ) {
    Firebase.auth.createUserWithEmailAndPassword(email,password)
        .addOnCompleteListener{ task ->
            if(task.isSuccessful){
                navController.navigate("login")
            } else {
                onError(task.exception?.message ?:
                "Registration failed")
            }
        }
}


@Preview(showBackground = true)
@Composable
fun RegisterUserPreview(){
    RegisterUser(rememberNavController(), onSignupClick = { email, password ->})
}