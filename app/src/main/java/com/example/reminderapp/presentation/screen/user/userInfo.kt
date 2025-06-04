package com.example.reminderapp.presentation.screen.user

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.reminderapp.presentation.screen.user.UserViewModel
import com.example.reminderapp.presentation.components.UserInfoCard
import com.example.reminderapp.R

@Composable
fun UserInfo(userViewModel: UserViewModel){
    val user=  userViewModel.user.value
    user?. let {
        UserInfoCard(profileImage= painterResource(id=
        R.drawable.ic_launcher_foreground),
            username = it.username,
            name = it.name,
            email = it.email,
            eventSaved = it.eventSaved,
            completed = it.completed,
            remaining = it.remaining
        )

    }


}