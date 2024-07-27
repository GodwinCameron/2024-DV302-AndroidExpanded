package co.za.openwindow.page.screens


import android.media.Image
import android.service.autofill.OnClickAction
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.za.openwindow.page.R
import co.za.openwindow.page.ui.theme.Background1
import co.za.openwindow.page.ui.theme.Background2
import co.za.openwindow.page.ui.theme.Background3
import co.za.openwindow.page.ui.theme.MessageTextColor
import co.za.openwindow.page.ui.theme.PageTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navigateToHome:() -> Unit = {},
    navigateToLogin:() -> Unit = {},
    modifier: Modifier = Modifier
) {

    val logo = painterResource(R.drawable.logo)
    var emailText = remember { mutableStateOf("") }
    var usernameText = remember { mutableStateOf("") }
    var passwordText = remember { mutableStateOf("") }
    var passwordCheckText = remember { mutableStateOf("") }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(Background1)
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Spacer(modifier = modifier.height(10.dp))
        Row(
            modifier = modifier.fillMaxWidth()
        ){
            Image(
                painter = logo,
                contentDescription = "Logo",
            )
        }

        Spacer(modifier = modifier.height(50.dp))
        Text("Create a new Page Account", color = Color.Gray, fontSize = 12.sp)
        Spacer(modifier = modifier.height(30.dp))




        InputField(iconVector = Icons.Default.Email, textFieldContent = emailText, fieldLabel = "Your Email", valid = true)
        InputField(iconVector = Icons.Default.Person, textFieldContent = usernameText, fieldLabel = "Choose a Username", valid = false)
        InputField(iconVector = Icons.Default.Lock, textFieldContent = passwordText, fieldLabel = "Choose a Password", valid = false)
        InputField(iconVector = Icons.Default.Refresh, textFieldContent = passwordCheckText, fieldLabel = "Repeat your new Password", valid = false)


        Row( // Button
            modifier = modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { navigateToHome.invoke() },
                modifier = modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Background3
                ),
            ) {
                Text("Register Account")
            }
        } // End of Button

        Spacer(modifier = modifier.height(20.dp))


        Row(
            horizontalArrangement = Arrangement.End,
            modifier = modifier.fillMaxWidth()
        ){
            TextButton(onClick = { navigateToLogin.invoke() }) {
                Text("Already have an Account? Login here!", color = Background3, fontSize = 12.sp)
            }
        }


    }
}




@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    PageTheme {
        RegisterScreen()
    }
}