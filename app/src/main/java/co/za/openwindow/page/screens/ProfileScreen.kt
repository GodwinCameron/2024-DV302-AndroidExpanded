package co.za.openwindow.page.screens

import android.media.Image
import android.service.autofill.OnClickAction
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.Edit
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
import co.za.openwindow.page.R
import co.za.openwindow.page.ui.theme.Background1
import co.za.openwindow.page.ui.theme.Background2
import co.za.openwindow.page.ui.theme.Background3
import co.za.openwindow.page.ui.theme.MessageTextColor
import co.za.openwindow.page.ui.theme.PageTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navigateToAllChats:() -> Unit = {},
    navigateToLogin:() -> Unit = {},
    modifier: Modifier = Modifier
) {

    val logo = painterResource(R.drawable.logo)
    val profileIcon = painterResource(R.drawable.test_profile_image_background)
    var emailText = remember { mutableStateOf("") }
    var passwordText = remember { mutableStateOf("") }


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
            Spacer(modifier = modifier.width(190.dp))
            Button(
                onClick = { navigateToLogin.invoke() },
                colors = ButtonDefaults.buttonColors( //From docs*
                    containerColor = Background3
                ),
            ) {
                Text("Signout")
            }
        }

        Spacer(modifier = modifier.height(50.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .background(color = Background3, shape = RoundedCornerShape(50))
                .width(150.dp)
                .height(150.dp)
        ){
            Image(
                painter = profileIcon,
                contentDescription = "Test Profile Icon"
            )
        }


        Spacer(modifier = modifier.height(30.dp))

        Row(
            modifier = modifier.fillMaxWidth()
        ){
            Text("Cameron", color = MessageTextColor)
            Spacer(modifier = modifier.width(10.dp))
            Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Icon", tint = MessageTextColor)
        }

        Spacer(modifier = modifier.height(10.dp))

        Row(
            modifier = modifier.fillMaxWidth()
        ){
            Text("cameron@test.com", color = MessageTextColor)
            Spacer(modifier = modifier.width(10.dp))
            Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Icon", tint = MessageTextColor)
        }

        Spacer(modifier = modifier.height(40.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxWidth()
        ){
            TextButton(onClick = { navigateToAllChats.invoke() }) {
                Text("Back to Chats", color = Background3, fontSize = 12.sp)
            }
        }



    }
}




@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    PageTheme {
        ProfileScreen()
    }
}
