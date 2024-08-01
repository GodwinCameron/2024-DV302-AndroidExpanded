package co.za.openwindow.page.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import co.za.openwindow.page.models.Message
import com.google.firebase.Firebase
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.firestore

class MessageViewModel (
    chatId: String = "1234"
) : ViewModel() {

    private var _messageList = mutableStateListOf<Message>()
    val messageList: List<Message> = _messageList

    // REAL TIME DATA LISTENER DECLARATION:
    var messageListener: ListenerRegistration? = null

    init {
        getRealTimeMessages(chatId)
    }

    //REAL TIME LISTENER FUNCTION
    fun getRealTimeMessages(id: String) {
        Log.d("CCC Listening for messages...", "...")

        //REFERENCE TO THE SPECIFIC CHAT'S COLLECTION OF MESSAGES.
        val messageRef = Firebase.firestore
            .collection("chats")
            .document(id)
            .collection("messages")

        messageListener = messageRef.addSnapshotListener { snapshot, error -> 
            Log.d("CCC Listening Starts...", "...")

            if (error != null) {
                Log.d("CCC Listening error:", error.localizedMessage.toString())
                return@addSnapshotListener
            }

            if (snapshot != null){
                Log.d("CCC Listening received...", snapshot.size().toString())
                _messageList.clear()
                for(document in snapshot) {
                    _messageList.add(document.toObject(Message::class.java))
                }
            }
        }

    }

}