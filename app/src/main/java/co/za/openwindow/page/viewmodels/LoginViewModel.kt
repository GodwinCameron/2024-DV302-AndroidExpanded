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

//THIS VIEWMODEL MANAGES FUNCTIONALITY FROM THE LOGIN VIEW WITHOUT MIXING LOGIC AND FRONTEND FOR CLEANER CODING.

//LOGIN VIEW LOGIC HERE (NEATENS VIEWS BY SPLITTING FUNCTIONALITY FROM FRONTEND):
class LoginViewModel(private val authRepository: AuthRepository = AuthRepository()): ViewModel() {

    //WITH A STATE FLOW CONNECTION, USING PRIVATE VALUES HELPS US MAINTAIN CONTROL OVER VALUES THAT
    //ARE PRESENT IN BOTH THE VIEW AND VIEWMODEL, BUT THAT WE WANT TO LIMIT CHANGING HERE (IN THE
    //LOGIC) AND NOT INTERFERE WITH THE DATA THAT EXISTS IN THE VIEW ITSELF.


    //PRIVATE SO THAT ONLY THIS FILE CAN CHANGE THIS VALUE, SHOWN WITH THE UNDERSCORE '_'
    private val _authState = MutableStateFlow(AuthUiState())

    //VALUE THAT GETS USED IN THE VIEW, NOW LINKED THROUGH STATEFLOW:
    val authState: StateFlow<AuthUiState> = _authState

    //HANDLES THE INPUT FIELDS' STATE VALUES, TARGET IS A STRING SINCE WE ENTER IT IN WITH KEYBOARD
    fun handleInputStateChanges(target: String, changedValue: String) {
        _authState.update { currentState ->
            when(target){
                "email" -> currentState.copy(email = changedValue)
                "password" -> currentState.copy(password = changedValue)
                else -> currentState
            }
        }
    }

    //LOGIN FUNCTION OF THE VIEW: (REQUIRES ACCESS TO FIELDS)
    fun login(){

        //ASYNC AWAIT FUNCTIONALITY
        viewModelScope.launch {

            //CHECKS FOR EMPTY INPUT FIELDS:
            try {
                //UTILIZES STATE FLOW CONNECTION TO LINK VIEWMODEL TO VIEW:
                authRepository.loginUser(_authState.value.email, _authState.value.password) {isCompleted ->

                    //'isCompleted' REPRESENTS A SUCCESSFUL LOGIN:
                    if (isCompleted) {
                        Log.d("CCC Current User: ", Firebase.auth.currentUser?.email.toString())
                        _authState.value = AuthUiState(error = "", success = true)
                        //IF Register -> Create new user in DB
                    } else {
                        Log.d("CCC Error Logging in user: ", "Something went wrong")
                        _authState.value = AuthUiState(error = "Something went wrong", success = false)
                    }
                }
            } catch(e:Exception){
                Log.d("CCC Error:", e.localizedMessage.toString())
                _authState.value = AuthUiState(error = e.localizedMessage.toString(), success = false)
            }
        }

    }
}

//DATA CLASS REPRESENTING ALL DATA ON VIEW FOR THE STATE FLOW CONNECTION:
data class AuthUiState (
    val email: String = "cameron@test.com",
    val password: String = "abcdef", // <- FIRESTORE REQUIRES A 6 CHARACTER MIN PASSWORD.
    val success: Boolean = false,
    val error: String = ""
)


