package co.za.openwindow.page.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.za.openwindow.page.repositories.AuthRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//ViewModel is the logic of our views (performs the functionality)
class LoginViewModel(private val authRepository: AuthRepository = AuthRepository()): ViewModel() {

    private val _authState = MutableStateFlow(AuthUiState())//private so that only this view model can change values
    val authState: StateFlow<AuthUiState> = _authState // variable we use in the view




    fun handleInputStateChanges(target: String, changedValue: String) { // <-- Target = which state we want to change
        _authState.update { currentState ->
            when(target){
                "email" -> currentState.copy(email = changedValue)
                "password" -> currentState.copy(password = changedValue)
                else -> currentState
            }
        }
    }

    //access to the email and password that the user typed in the VIEW
    fun login(){

        viewModelScope.launch { // for Async/Await functionality
            // if email and/or password is blank - error message = add email/password
            try {
                authRepository.loginUser(_authState.value.email, _authState.value.password) {isCompleted ->
                    if (isCompleted) {
                        Log.d("AAA Current User", Firebase.auth.currentUser?.email.toString())
                        _authState.value = AuthUiState(error = "", success = true)
                        //IF Register -> Create new user in DB
                    } else {
                        Log.d("AAA Error Logging in user: ", "Something went wrong")
                        _authState.value = AuthUiState(error = "Something went wrong", success = false)
                    }
                }
            } catch(e:Exception){
                Log.d("AAA Error:", e.localizedMessage.toString())
                _authState.value = AuthUiState(error = e.localizedMessage.toString(), success = false)
            }
        }

    }
}

//Create a data class - represents all the variables in view
data class AuthUiState (
    val email: String = "cameron@test.com",
    val password: String = "123456", // 6 character minimum for FireStore
    val success: Boolean = false,
    val error: String = ""
)


