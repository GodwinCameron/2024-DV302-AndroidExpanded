package co.za.openwindow.page

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import co.za.openwindow.page.ui.theme.PageTheme
import co.za.openwindow.page.viewmodels.AuthViewModel
import co.za.openwindow.classworkapplication.Navigation
import com.google.firebase.FirebaseApp
import androidx.compose.ui.Modifier


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this) // Initialize Firebase before setContent
        setContent {
            val authViewModel: AuthViewModel = viewModel(modelClass = AuthViewModel::class.java)

            PageTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Navigation(authViewModel = authViewModel)
                }
            }
        }
    }
}
