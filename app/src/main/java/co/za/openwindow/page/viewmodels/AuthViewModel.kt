package co.za.openwindow.page.viewmodels

import androidx.lifecycle.ViewModel
import co.za.openwindow.page.repositories.AuthRepository


// Check current logged in user's info and state
class AuthViewModel(private val authRepository: AuthRepository = AuthRepository()): ViewModel() {

    val currentUserInfo = authRepository.currentUser

    val userState: Boolean
        get() = authRepository.hasUser()

    fun signUserOut() {
        authRepository.logOff()
    }

}