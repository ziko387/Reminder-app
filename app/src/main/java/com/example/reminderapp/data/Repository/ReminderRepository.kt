package com.example.reminderapp.data.Repository



import com.example.reminderapp.data.model.Reminder
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.emptyFlow
import java.time.LocalDateTime
import java.time.ZoneOffset
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.flow
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import  com.google.common.primitives.UnsignedInts.remainder
import kotlinx.coroutines.tasks.await


interface  ReminderRepository{
    fun fetchRemaindersFromFirebase(): Flow<List<Reminder>>
    suspend fun addRemainderToFirebase(reminder: Reminder)
    suspend fun updateReminderToFirebase(reminder: Reminder)
     fun deleteReminderToFirebase(reminder: Reminder)
    fun getUpcomingRemindersToFirebase(userId: Int): Flow<List<Reminder>>
    fun getCompletedRemindersToFirebase(userId: Int): Flow<List<Reminder>>

}
class ReminderRepositoryImpl():
ReminderRepository{

    override fun fetchRemaindersFromFirebase(): Flow<List<Reminder>>
    = callbackFlow {
      //  val db = Firebase.firestore
     //   val remindersCollection = db.collection("reminders")
      //  val snapshotListener = remindersCollection.addSnapshotListener { snapshot, error ->
          //  if (error != null) {
            //    close(error)
              //  return@addSnapshotListener
           // }
           // if (snapshot != null) {

               // val reminders = snapshot.documents.mapNotNull { document ->
                  //  document.toObject(Reminder::class.java)
               // }
            //    trySend(reminders)

          //  } else {
                trySend(emptyList<Reminder>())
         //   }
         //   close()


       // }
      //  awaitClose {
        //    snapshotListener.remove()
        //}
        val storage = FirebaseDatabase.getInstance().reference.child("reminders")

           val listener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val reminders = mutableListOf<Reminder>()
                    for (child in snapshot.children) {
                        val remainder = child.getValue(Reminder::class.java)
                        remainder?.let { reminders.add(it) }
                    }
                    trySend(reminders).isSuccess

                }

               override fun onCancelled(error: DatabaseError) {
                   close(error.toException())


               }
           }
        storage.addValueEventListener(listener)
        // if app is not launched close the connection
        awaitClose{storage.removeEventListener(listener)}


    }


    override suspend fun addRemainderToFirebase(reminder: Reminder) {
            val database = FirebaseDatabase.getInstance().reference
        val newRemainder= database.child("reminders").push()
    val firebase_id= newRemainder.key?:return
        val remainderWithId=reminder.copy(firebaseId = firebase_id)
        newRemainder.setValue(remainderWithId)

    }

    override suspend fun updateReminderToFirebase(reminder: Reminder) {
        val firebase_id= reminder.firebaseId
        if(firebase_id.isNotEmpty()){
            val storage=  FirebaseDatabase.getInstance().reference
                .child("reminders").child(firebase_id)
            storage.setValue(reminder).await()

        }else{
            throw IllegalArgumentException("Firebase id is empty")
        }
    }

    override  fun deleteReminderToFirebase(reminder: Reminder) {
        val firebase_id= reminder.firebaseId
        if(firebase_id.isNotEmpty()){
            val storage=  FirebaseDatabase.getInstance().reference
                .child("reminders").child(firebase_id)
            storage.removeValue()

        }else{
            throw IllegalArgumentException("Firebase id is empty")
        }
    }

    override fun getUpcomingRemindersToFirebase(userId: Int): Flow<List<Reminder>> {
        TODO("Not yet implemented")

    }

    override fun getCompletedRemindersToFirebase(userId: Int): Flow<List<Reminder>> {
        TODO("Not yet implemented")
    }


}





















