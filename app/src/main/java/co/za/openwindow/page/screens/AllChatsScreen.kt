package co.za.openwindow.page.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.za.openwindow.page.R
import co.za.openwindow.page.ui.theme.Background1
import co.za.openwindow.page.ui.theme.Background2
import co.za.openwindow.page.ui.theme.Background3
import co.za.openwindow.page.ui.theme.Background4
import co.za.openwindow.page.ui.theme.DarkGrayText
import co.za.openwindow.page.ui.theme.DarkText
import co.za.openwindow.page.ui.theme.PageTheme
import co.za.openwindow.page.viewmodels.ChatViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import co.za.openwindow.page.models.Chat

@Composable
fun AllChatsScreen(
    viewModel: ChatViewModel = viewModel(),
    navigateToChat:(chatId: String) -> Unit = {},
    navigateToProfile:() -> Unit = {},
    modifier: Modifier = Modifier
){

    //VALUE FOR DYNAMIC DATA
    val chats:List<Chat> = viewModel.chatList


    val logo = painterResource(R.drawable.logo)
    val chatsSectionBorder = RoundedCornerShape(
        topStart = 50.dp,
        topEnd = 50.dp,
        bottomStart = 0.dp,
        bottomEnd = 0.dp
    )

    Column( // Parent Div
        modifier = modifier
            .background(Background2)
            .fillMaxSize()
    ){
        Row( // Top
            modifier = modifier
                .background(Background2)
                .fillMaxWidth()
                .height(130.dp)
                .padding(10.dp) // Styling
        ){
            Column(){
                Image(
                    painter = logo,
                    contentDescription = "Logo"
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row() {
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(onClick = { navigateToProfile.invoke() },
                        colors = ButtonDefaults.buttonColors( //From docs*
                            containerColor = Background2
                        ),) {
                        ProfileIcon()
                    }

                }
            }
        } // End of Top

        Column( // User Chats Area
            modifier = modifier
                .fillMaxWidth()
                .background(color = Background1, shape = chatsSectionBorder)
                .height(600.dp)
                .padding(20.dp) // Styling
        ) {
            LazyColumn(
                modifier = modifier.padding(10.dp)
            ) {
                items(chats){chat ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier.clickable { navigateToChat.invoke(chat.id) }
                    ){
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(50.dp)
                                .background(color = Color.White, shape = RoundedCornerShape(50))

                        ) {
                            Box(
                                modifier = Modifier
                                    .size(45.dp)
                                    .background(color = Background2, shape = RoundedCornerShape(50))
                            )
                        }
                        Spacer(modifier = Modifier.width(15.dp))
                        Column {
                            Text(chat.name, color = Color.White)
                            Text("Last message - date.", color = Color.Gray, fontSize = 10.sp)
                        }
                    }
                    Spacer(modifier = modifier.height(15.dp))
                }
            }

        } // End of User Chats Area

    } // End of Parent Div
} // ChatScreen Function

@Preview(showBackground = true)
@Composable
fun AllChatsScreenPreview() {
    PageTheme {
        AllChatsScreen()
    }
}

@Composable
fun ProfileIcon(
    modifier: Modifier = Modifier
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(65.dp)
            .background(color = Color.White, shape = RoundedCornerShape(50))

    ) {
        Box(
            modifier = Modifier
                .size(58.dp)
                .background(color = Background2, shape = RoundedCornerShape(50))
        )
    }
    Spacer(modifier = Modifier.width(15.dp))
}