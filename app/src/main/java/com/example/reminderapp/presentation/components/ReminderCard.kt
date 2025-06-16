package com.example.reminderapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import com.example.reminderapp.data.model.Reminder
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import java.time.LocalDateTime
import androidx.compose.ui.graphics.Color





@Composable
fun ReminderCard(
    reminder: Reminder
){

        Card(
            modifier = Modifier.padding(vertical = 6.dp).clip(RoundedCornerShape(22.dp))
                .fillMaxWidth().clickable{},
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Cyan)



        ) {
            Column (modifier = Modifier.padding(4.dp)){
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Face,
                        contentDescription = "name",
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = reminder.name,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(start = 8.dp)

                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = reminder.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = reminder.datetime.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.weight(1f))
                    Button(onClick = {},colors = ButtonDefaults.buttonColors(containerColor =Color.Red )) {
                        Text(
                            text = "Delete",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                   Button(onClick = {},colors = ButtonDefaults.buttonColors(containerColor =Color.Red )) {
                       Text(
                           text = "Edit",
                           style = MaterialTheme.typography.bodyMedium,
                       )
                   }
                }
            }

        }
    }

@Preview
@Composable
fun ReminderCardPreview(){
    ReminderCard(
        reminder = Reminder(
            name = "party",
            description = "going to musa for party",
            datetime = LocalDateTime.now()
        )
    )
}