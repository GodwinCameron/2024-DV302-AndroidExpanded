package co.za.openwindow.page.repositories

import android.util.Log
import co.za.openwindow.page.models.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


//Repository - viewModels connection to our firebase - communication with backend
class AuthRepository {

    // Firebase functionality

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    //check logged in users info
    val currentUser: FirebaseUser? = Firebase.auth.currentUser // <-- get the auth user info
    fun hasUser(): Boolean = Firebase.auth.currentUser != null // <-- check if there is a user
    fun getUserId(): String = Firebase.auth.currentUser?.uid.orEmpty() // <-- get the user id

    //Log user out of firebase
    fun logOff(){
        Firebase.auth.signOut()
        Log.d("AAA Current User", "log off success")
    }

    //TODO: register a new user
    suspend fun createNewAccount(
        username: String,
        email: String,
        password: String,
        isCompleted: (Boolean) -> Unit //callback function to send true/false back depending on success
    ) = withContext(Dispatchers.IO){ // Like an async/await function

        Firebase.auth
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                if(it.isSuccessful){
                    Log.d("AAA Register State: ", "login successful")

                    var userDb = Firebase.firestore.collection("users")

                    it.result.user?.uid?.let { it1 ->
                        userDb.document(it1).set(
                            User(
                                id = it1,
                                email = email,
                                username = username,
                            )
                        ).addOnSuccessListener { newUser ->
                            Log.d("AAA New User DB: created", "hooray!")
                            isCompleted.invoke(true)
                        }.addOnFailureListener {e ->
                            isCompleted.invoke(false)
                            Log.d("AAA Register State: ", e.localizedMessage.toString())
                        }
                    }

                    isCompleted.invoke(true)
                } else {
                    Log.d("AAA Register State: ", it.exception?.localizedMessage.toString())
                    isCompleted.invoke(false)
                }
            }.await()
    }
    //TODO: login an existing user
    suspend fun loginUser(
        email: String,
        password: String,
        isCompleted: (Boolean) -> Unit //callback function to send true/false back depending on success
    ) = withContext(Dispatchers.IO){ // Like an async/await function

        Firebase.auth
//            .createUserWithEmailAndPassword()  <-- Registration
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                if(it.isSuccessful){
                    Log.d("AAA Login State: ", "login successful")
                    isCompleted.invoke(true)
                } else {
                    Log.d("AAA Login State: ", it.exception?.localizedMessage.toString())
                    isCompleted.invoke(false)
                }
            }.await()
    }


}