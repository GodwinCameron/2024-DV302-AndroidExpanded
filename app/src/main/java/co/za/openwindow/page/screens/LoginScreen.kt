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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import co.za.openwindow.page.R
import co.za.openwindow.page.ui.theme.Background1
import co.za.openwindow.page.ui.theme.Background2
import co.za.openwindow.page.ui.theme.Background3
import co.za.openwindow.page.ui.theme.MessageTextColor
import co.za.openwindow.page.ui.theme.PageTheme
import co.za.openwindow.page.viewmodels.LoginViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
    navigateToRegister:() -> Unit = {},
    navigateToHome:() -> Unit = {},
    modifier: Modifier = Modifier
) {

    val logo = painterResource(R.drawable.logo)

    val loginState by viewModel.authState.collectAsState()


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(Background1)
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Spacer(modifier = modifier.height(80.dp))
        Image(
            painter = logo,
            contentDescription = "Logo",
        )
        Spacer(modifier = modifier.height(50.dp))
        Text("Login with your Page account", color = Color.Gray, fontSize = 12.sp)
        Spacer(modifier = modifier.height(30.dp))

//        InputField(
//            iconVector = Icons.Default.Email,
//            textFieldContent = loginState.email,
//            fieldLabel = "Email",
//            valid = true
//        )
//        InputField(
//            iconVector = Icons.Default.Lock,
//            textFieldContent = loginState.password,
//            fieldLabel = "Password",
//            valid = true
//        )

        Row ( // Custom Field
            modifier = modifier.fillMaxWidth()
        ){
            Column(
                modifier = modifier
            ) {
                TextField(
                    value = loginState.email,
//                onValueChange = { textFieldContent.value = it },
                    onValueChange = { /*TODO*/ },

                    label = { Text("Email") },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Email, contentDescription = "Field Icon")
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Background2,
                        focusedLabelColor = Color.Gray,
                        unfocusedLabelColor = Color.Gray,
                        unfocusedLeadingIconColor = Color.Gray,
                        focusedLeadingIconColor = Color.Gray,
                        focusedTextColor = Color.Gray,
                        unfocusedTextColor = Color.Gray,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(9.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = modifier
                        .fillMaxWidth()
                )
            }
        } // End of Custom Field
        Spacer(modifier = modifier.height(10.dp))
        Row ( // Custom Field
            modifier = modifier.fillMaxWidth()
        ){
            Column(
                modifier = modifier
            ) {
                TextField(
                    value = loginState.password,
//                onValueChange = { textFieldContent.value = it },
                    onValueChange = { /*TODO*/ },

                    label = { Text("Password") },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Lock, contentDescription = "Field Icon")
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Background2,
                        focusedLabelColor = Color.Gray,
                        unfocusedLabelColor = Color.Gray,
                        unfocusedLeadingIconColor = Color.Gray,
                        focusedLeadingIconColor = Color.Gray,
                        focusedTextColor = Color.Gray,
                        unfocusedTextColor = Color.Gray,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(9.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = modifier
                        .fillMaxWidth()
                )
            }
        } // End of Custom Field
        Spacer(modifier = modifier.height(10.dp))

        Row( // Button
            modifier = modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { navigateToHome.invoke() },
                modifier = modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors( //From docs*
                    containerColor = Background3
                ),
            ) {
                Text("Login")
            }
        } // End of Button

        Spacer(modifier = modifier.height(20.dp))


        Row(
            horizontalArrangement = Arrangement.End,
            modifier = modifier.fillMaxWidth()
        ){
            TextButton(onClick = { viewModel.login() }) {
                Text("Create a New Account Here!", color = Background3, fontSize = 12.sp)
            }
        }


    }
}




@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    PageTheme {
        LoginScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    iconVector: ImageVector,
    textFieldContent: MutableState<String>,
    fieldLabel: String,
    valid: Boolean,
    modifier: Modifier = Modifier,

    ){

    Row ( // Custom Field
        modifier = modifier.fillMaxWidth()
    ){
        Column(
            modifier = modifier
        ) {
            val trailingIcon = if (valid) {
                Icons.Default.Check
            } else {
                Icons.Default.Clear
            }
            TextField(
                value = textFieldContent.value,
//                onValueChange = { textFieldContent.value = it },
                onValueChange = { /*TODO*/ },

                label = { Text(fieldLabel) },
                leadingIcon = {
                    Icon(imageVector = iconVector, contentDescription = "Field Icon")
                },
                trailingIcon = {
                   Icon(imageVector = trailingIcon, contentDescription = "Field Checker Icon")
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Background2,
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray,
                    unfocusedLeadingIconColor = Color.Gray,
                    focusedLeadingIconColor = Color.Gray,
                    focusedTextColor = Color.Gray,
                    unfocusedTextColor = Color.Gray,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(9.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = modifier
                    .fillMaxWidth()
            )
        }
    } // End of Custom Field
    Spacer(modifier = modifier.height(10.dp))

}

