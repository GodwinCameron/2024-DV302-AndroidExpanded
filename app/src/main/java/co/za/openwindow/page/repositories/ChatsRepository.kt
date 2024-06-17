package co.za.openwindow.page.repositories

import android.util.Log
import co.za.openwindow.page.models.Chat
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

const val chatDB = "chats" // macth name in db

class ChatsRepository {

    val db = Firebase.firestore

    private val chatsCollection = db.collection(chatDB)

    suspend fun getAllChats(
        onComplete: (List<Chat>?) -> Unit
    ) {

        Log.d("AAA getting subjects...", "loading")

        var chats:ArrayList<Chat> = arrayListOf<Chat>()
        chatsCollection.get()
            .addOnSuccessListener { documents ->
                for(doc in documents) {
                    chats.add(
                        Chat(
                            id = doc.id,
                            name = doc.data["name"].toString(),
                        )
                    )
                }

                Log.d("AAA Amount of data ", chats.size.toString())
                onComplete.invoke(chats)
            }
            .addOnFailureListener { e ->
                Log.d("AAA Error getting chats", e.localizedMessage.toString())
                onComplete.invoke(null)
            }

    }
}