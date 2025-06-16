package com.example.reminderapp.presentation.screen.Dasboard

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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRemainderScreen(
    reminder: Reminder,

){
   var name by remember { mutableStateOf("") }
   var description by remember { mutableStateOf("") }
   var calender = Calendar.getInstance()
    var context = LocalContext.current
    var datePickerController by remember { mutableStateOf(false) }
    var selectedTime by remember { mutableStateOf("") }
    val dateState = rememberDatePickerState()

    var selectDate by remember { mutableStateOf(Date().time) }
    val reminder= Reminder(
        name = name,
        description = description,
        datetime = LocalDateTime.now(),
        isCompleted = false

    )




       
    
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
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
          datePickerController=true
            }) {
                Text(
                    text = "Select Date:${convertLongDates(selectDate)}",

                )
            }
             if (datePickerController){
                 DatePickerDialog(
                     onDismissRequest = {datePickerController=false},
                     confirmButton = {
                         Button(onClick = {
                             if (dateState.selectedDateMillis!=null){
                                selectDate=dateState.selectedDateMillis!!
                             }



                             datePickerController=false
                         }) {
                             Text(text = "Confirm")
                         }
                     },
                     dismissButton = {
                         Button(onClick = {datePickerController=false}) {
                             Text(text = "Dismiss")
                         }
                     }
                 ) {
                     DatePicker(
                         state = dateState
                     )
                 }
             }
            
            
            
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {
                timePickerDialog.show()
            }) {
                Text(
                    text = "Select Time",
                    fontWeight = FontWeight.Bold
                )

            }
        }
        Spacer(modifier = Modifier.height(10.dp).width(100.dp))

        Button(onClick = {
            val reminder = Reminder(
                name = name,
                description = description,
                datetime = LocalDateTime.now()

            )
            

        }, modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
            Text(
                text = "Add Reminder",
                fontWeight = FontWeight.Bold
            )


        }
    }



            }
private fun convertLongDates(date: Long): String{
    val dateNew= Date(date)
    val format= SimpleDateFormat.getDateInstance()
    return format.format(dateNew)

}


@Preview(showBackground = true)
@Composable
fun AddRemainderScreenPreview(){
    AddRemainderScreen(
        reminder = Reminder(
        name = "party",
        description = "going to musa for party",
        datetime = LocalDateTime.now()
        )
    )
}







