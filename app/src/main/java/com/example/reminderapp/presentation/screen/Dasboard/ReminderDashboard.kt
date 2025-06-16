package com.example.reminderapp.presentation.screen.Dasboard


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.AppBarDefaults
import com.example.reminderapp.presentation.screen.Dasboard.DashBoardViewModel
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.BottomAppBar
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.reminderapp.presentation.components.ReminderCard
import com.example.reminderapp.presentation.screen.Dasboard.DashBoardScreen
import com.example.reminderapp.data.model.Reminder
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import com.example.reminderapp.R
import androidx.compose.ui.graphics.Color
import androidx.compose.material.DrawerDefaults.backgroundColor
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem




@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterial3Api
@Composable
fun DashBoardScreen(
    navController: NavController,

    ) {
    Scaffold (
        topBar = { TopAppbar() },
        bottomBar = { BottomBar() }
    ){ innerPadding ->
        Column (
            modifier = Modifier.padding(innerPadding).
            verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            BoxRemainder()
        }

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppbar(){
    TopAppBar(
        title = {
            Row (){
                Image(
                    painter = painterResource(id = R.drawable.elon),
                    contentDescription = "REMINDER LOGO",
                    modifier = Modifier.height(24.dp),

                    )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "REMINDER")
            }


        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add") }
        },


        )


}
@Composable
fun BoxRemainder(){

}
@Composable
fun BottomBar(){
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favorite") },
            label = { Text("Favorite") },
            selected = false,
            onClick = { /*TODO*/ }

        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Add, contentDescription = "Add") },
            label = { Text("Add") },
            selected = false,
            onClick = { /*TODO*/ })
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Refresh, contentDescription = "Refresh") },
            label = { Text("Refresh") },
            selected = false,
            onClick = { /*TODO*/ })
        NavigationBarItem(
            icon = { Icon(Icons.Filled.AccountCircle, contentDescription = "PersonInfo") },
            label = { Text("Info") },
            selected = false,
            onClick = { /*TODO*/ })


    }



}
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DashBoardPreview(){
    DashBoardScreen(navController = rememberNavController())

}


