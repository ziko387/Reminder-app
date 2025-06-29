package com.example.reminderapp.presentation.screen.Dasboard

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.renderscript.Sampler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDateTime
import androidx.compose.ui.unit.dp
import android.widget.TimePicker
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.reminderapp.data.Repository.ReminderRepository
import java.time.LocalTime
import com.example.reminderapp.data.model.Reminder
import java.text.SimpleDateFormat
import java.util.Date
import java.sql.Time
import androidx.compose.foundation.layout.Arrangement
import com.example.reminderapp.presentation.screen.Dasboard.DashBoardViewModel
import com.example.reminderapp.data.Repository.ReminderRepositoryImpl



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRemainderScreen(
    viewModel: DashBoardViewModel,
    onDismiss: () -> Unit,



){
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var calender = Calendar.getInstance()
    var context = LocalContext.current
    var datePickerController by remember { mutableStateOf(false) }
    var selectedTime by remember { mutableStateOf("") }
    val dateState = rememberDatePickerState()
    var selectedDate by remember { mutableStateOf("") }


    var selectDate by remember { mutableStateOf(Date().time) }








    val timePickerDialog = TimePickerDialog(
        context,{ _: TimePicker, hourOfDay: Int, minute: Int ->
            selectedTime = "$hourOfDay:$minute"
        },calender.get(Calendar.HOUR_OF_DAY),
        calender.get(Calendar.MINUTE),
        true
    )


    Column(modifier = Modifier.padding(18.dp)) {
        //
        Text(
            text = "Add Reminder",
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif

        )
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text(text = "Description") },
            modifier = Modifier.fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(10.dp))
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Button(onClick = {
                datePickerController = true
            }) {
                Text(
                    text = "Select Date:${convertLongDates(selectDate)}",

                    )
            }
            if (datePickerController) {
                DatePickerDialog(
                    onDismissRequest = { datePickerController = false },
                    confirmButton = {
                        Button(onClick = {
                            if (dateState.selectedDateMillis != null) {
                                selectDate = dateState.selectedDateMillis!!
                            }



                            datePickerController = false
                        }) {
                            Text(text = "Confirm")
                        }
                    },
                    dismissButton = {
                        Button(onClick = { datePickerController = false }) {
                            Text(text = "Dismiss")
                        }
                    }
                ) {
                    DatePicker(
                        state = dateState
                    )
                }
            }


            Button(onClick = {
                timePickerDialog.show()
            }) {
                Text(
                    text = "Select Time",
                    fontWeight = FontWeight.Bold
                )

            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
        Button(
            onClick = {
                if(name.isNotBlank()){
                    viewModel.addRemainder(name,description,
                        datetime = selectDate,false)
                    onDismiss()
                }

            },enabled = name.isNotBlank()

        ) {
            Text(
                text = "Add Reminder",
                fontWeight = FontWeight.Bold
            )


        }
            Button(
                onClick = {
                    onDismiss()

                }){
                Text(
                    text = "Cancel",
                    fontWeight = FontWeight.Bold
                )
            }
    }
    }



}
private fun convertLongDates(date: Long): String{
    val dateNew= Date(date)
    val format= SimpleDateFormat.getDateInstance()
    return format.format(dateNew)

}


@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun AddRemainderScreenPreview(

){
    var view_Model = DashBoardViewModel(ReminderRepositoryImpl())
    AddRemainderScreen(

        viewModel = view_Model,
        onDismiss = {}
    )
}