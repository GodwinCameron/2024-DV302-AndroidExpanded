package co.za.openwindow.page.models

import com.google.firebase.Timestamp

data class Message(
    val text: String = "",
    val from: String = "",
    val fromUserId: String = "",
    val time: Timestamp = Timestamp.now()
)