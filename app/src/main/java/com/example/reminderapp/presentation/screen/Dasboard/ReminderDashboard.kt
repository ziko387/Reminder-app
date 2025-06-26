package com.example.reminderapp.presentation.screen.Dasboard


import android.content.Context
import android.graphics.Paint
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.reminderapp.R
import com.example.reminderapp.data.Repository.ReminderRepositoryImpl
import com.example.reminderapp.data.model.Reminder
import com.example.reminderapp.presentation.components.ReminderCard
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars


@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterial3Api
@Composable
fun DashBoardScreen(

    navController: NavController,
    viewModel: DashBoardViewModel = viewModel(
        factory = DashBoardViewModelFactory (ReminderRepositoryImpl(

        ))
    )

    ) {

    val context: Context = LocalContext.current
    Scaffold(
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 2.dp),

        topBar = {
            TopAppbar(


            )
        },
        bottomBar = {
            BottomBar(
                viewModel = viewModel


            )
        }

    )
    { innerPadding ->
        val modifier = Modifier.padding(innerPadding)

        //Column (
       // modifier = Modifier.padding(innerPadding).
      //  verticalScroll(rememberScrollState()),
      //  horizontalAlignment = Alignment.CenterHorizontally,
        //)

        BoxRemainder(

        )


    }

    }






//    val viewModel: DashBoardViewModel = viewModel(
//        factory = DashBoardViewModelFactory(ReminderRepositoryImpl())
//    )


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopAppbar(

    ) {
        val showAddDialog = remember { mutableStateOf(false) }
        val repository = ReminderRepositoryImpl()
        val viewModel: DashBoardViewModel = viewModel(
            factory = DashBoardViewModelFactory(ReminderRepositoryImpl())
        )

        TopAppBar(
            title = {
                Row() {
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
                IconButton(onClick = { showAddDialog.value = true }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add"
                    )
                }
            },

            )
        if (showAddDialog.value) {
            // show pop up
            // AlertDialog is used to show pop ups
            AlertDialog(
                onDismissRequest = { showAddDialog.value = false },
                title = { Text("Add Todo") },
                text = {
                    AddRemainderScreen(
                        viewModel = viewModel(),
                        onDismiss = { showAddDialog.value = false }
                    )

                },
                confirmButton = {},
                dismissButton = {}
            )
        }
    }


    @Composable
    fun BoxRemainder(



    ) {
        val viewModel: DashBoardViewModel = viewModel(
            factory = DashBoardViewModelFactory(ReminderRepositoryImpl())
        )
        val RemainderBeingEdited = remember { mutableStateOf<Reminder?>(null) }

        val firebaseReminder = viewModel.firebaseReminders.collectAsState()


        LazyColumn(
            modifier = Modifier.padding(26.dp)
        ) {
            items(firebaseReminder.value.size) { reminder ->
                ReminderCard(
                    reminder = firebaseReminder.value[reminder],
                    onEditClick = {
                        RemainderBeingEdited.value = firebaseReminder.value[reminder]
                    },
                    onDeleteClick = {
                        viewModel.deleteRemainder(firebaseReminder.value[reminder])
                    },


                    )
            }

        }
        Spacer(modifier = Modifier.height(16.dp))


    }


    @Composable
    fun BottomBar(
        viewModel: DashBoardViewModel = viewModel(
            factory = DashBoardViewModelFactory(ReminderRepositoryImpl())
        )

    ) {

        val showAddDialog = remember { mutableStateOf(false) }
        val showEditDialog = remember { mutableStateOf(false) }
        val RemainderBeingEdited = remember { mutableStateOf<Reminder?>(null) }
        val repository = ReminderRepositoryImpl()
        val viewModel: DashBoardViewModel = viewModel(
            factory = DashBoardViewModelFactory(ReminderRepositoryImpl())
        )


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
                onClick = { showAddDialog.value = true })
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
        if (showAddDialog.value) {
            // show pop up
            //AlertDialog is used to show pop ups
            AlertDialog(
                onDismissRequest = { showAddDialog.value = false },
                title = { Text("Add reminder") },
                text = {
                    AddRemainderScreen(
                        viewModel = viewModel(),
                        onDismiss = { showAddDialog.value = false }
                    )

                },
                confirmButton = {},
                dismissButton = {}
            )
        }

        //if(showEditDialog.value &&
        //  RemainderBeingEdited.value != null){
        // show pop up
        // AlertDialog(
        //  onDismissRequest = { showEditDialog.value = false},
        // title = { Text("Edit Todo") },
        //   text = {
        //  EditRemainderScreen(
        //     todo = RemainderBeingEdited.value!!,
        //   onSubmit = {updateReminder ->
        //    viewModel.updateRemainder(
        //       updateReminder


        //  )
        //  },
        //   onDismiss = {showEditDialog.value= false}
        //  )
        // },
        // confirmButton = {}
        //)
        //}


    }






