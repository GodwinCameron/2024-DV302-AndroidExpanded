package co.za.openwindow.page.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
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
import co.za.openwindow.page.ui.theme.MessageBlockColor
import co.za.openwindow.page.ui.theme.MessageReplyBlockColor
import co.za.openwindow.page.ui.theme.MessageTextColor
import co.za.openwindow.page.ui.theme.PageTheme
import co.za.openwindow.page.ui.theme.PagedResponseBlock
import co.za.openwindow.page.ui.theme.Purple80

@Composable
fun ChatScreen(
    modifier: Modifier = Modifier
){


    val backButtonIcon = painterResource(R.drawable.backbuttonicon)
    val sendMessageIcon = painterResource(R.drawable.sendmessageicon)
    val writeBorderRadius = RoundedCornerShape(
        topStart = 20.dp,
        topEnd = 20.dp,
        bottomStart = 20.dp,
        bottomEnd = 20.dp
    )

    Column( // Parent Div
        modifier = modifier
            .background(Background1)
            .fillMaxSize()
    ){

        Row( // Top Chat Bar with user info and back button etc.
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .background(Background2)
                .fillMaxWidth()
                .height(95.dp)
                .padding(10.dp) // Styling
        ){
            Image(
                painter = backButtonIcon,
                contentDescription = "Back Button Icon"
            )
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(65.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(50))

            ) {
                Box(
                    modifier = Modifier
                        .size(58.dp)
                        .background(color = Background3, shape = RoundedCornerShape(50))
                )
            }
            Spacer(modifier = Modifier.width(24.dp))
            Text(
                text = "Hugh Mongus",
                color = Color.White,
                fontSize = 18.sp
            )
        } // End of Top Chat Bar

        Column( // Chat and message Area
            modifier = modifier
                .fillMaxWidth()
                .height(550.dp)
                .padding(20.dp) // Styling
        ) {

//            Message(text = "hi there, can I interest you in some random things that I need to fill this field out please, it would really help me if this could be a test for a message")
//
//            Reply(text = "Sorry, I'm not interested in your message, so this is my reply.")
//
//            Reply(text = "Here is a short one.")
//
//            Message(text = "Well then here is a new message.")

            Message(text="Hi there!")
            Reply(text="Hi!")
            Message(text="How are you?")
            Reply(text="I'm good! Did you catch last night's Rugby match?")




        } // End of Chat and message Area

        Box( // Paged Responses Block
            modifier = modifier
                .padding(10.dp)
        ){
            Box(
                modifier = modifier
                    .background(Background2, shape = RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .height(100.dp)
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(2.dp)
                ){
                    Text(
                        text = "Paged Responses",
                        color = DarkGrayText,
                        fontSize = 9.sp
                    )
                    Spacer(modifier = modifier.height(5.dp))
                    Row( // Page Response Container
                        modifier = modifier
                    ){
                        PageBlock(text = "On My Way!")
                        PageBlock(text = "Can't Make it")
                        PageBlock(text = "New page")


                    } // End of Page Response Container
                }
            }
        } // End of Paged Responses Block

        Spacer(modifier = modifier.height(18.dp))

        Row( // Write a message Section
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .background(Background2)
                .fillMaxWidth()
                .height(70.dp)
                .padding(10.dp) // Styling
        ){
            Spacer(modifier = modifier.width(10.dp))
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = modifier
                    .width(300.dp)
                    .height(30.dp)
                    .background(color = Background4, shape = writeBorderRadius)
            ){
                Text("      Write a message...", color = DarkText, fontSize = 12.sp)
            }
            Spacer(modifier = modifier.width(28.dp))
            Image(
                painter = sendMessageIcon,
                contentDescription = "Send Message Button Icon"
            )
        }// End of Write a message Section

    } // End of Parent Div
} // ChatScreen Function

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    PageTheme {
        ChatScreen()
    }
}




@Composable
fun Message(
    modifier: Modifier = Modifier,
    text: String
) {
    val messageInBorderRadius = RoundedCornerShape(
        topStart = 10.dp,
        topEnd = 10.dp,
        bottomStart = 0.dp,
        bottomEnd = 10.dp
    )

    Row( // Message
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = modifier
//Ask Armand for help here
//                        .height(200.dp)
        ) {
            Box( // User Icon
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(20.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(50)) // Styling
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(color = Background3, shape = RoundedCornerShape(50))
                )
            } // End of User Icon
        }


        Spacer(modifier = Modifier.width(16.dp))

        Box(
            modifier = Modifier
                .background(color = MessageBlockColor, shape = messageInBorderRadius)
                .padding(10.dp)

        ) {
            Text(
                text = text,
                color = MessageTextColor,
                fontSize = 12.sp,
                lineHeight = 17.sp
            )
        }


    } // End of Message
    Spacer(modifier = Modifier.height(16.dp))
}
@Composable
fun Reply(
    modifier: Modifier = Modifier,
    text: String
) {

    Row( // User Reply
        horizontalArrangement = Arrangement.End,
        modifier = modifier
            .fillMaxWidth()
    ){
        Box(
            modifier = Modifier
                .background(color = MessageReplyBlockColor, shape = RoundedCornerShape(10.dp))
                .padding(10.dp)

        ) {
            Text(
                text = text,
                color = Color.Black,
                fontSize = 12.sp,
                lineHeight = 17.sp
            )
        }
    } // End of User Reply
    Spacer(modifier = Modifier.height(16.dp))

}

@Composable
fun PageBlock(
    modifier : Modifier = Modifier,
    text: String,
    grade: Number = 1
){
    Box( // Page Response Block
        modifier = modifier
            .size(60.dp)
            .background(color = PagedResponseBlock, shape = RoundedCornerShape(8.dp))
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth()
        ){
            Text(text, color = Color.White, fontSize = 10.sp, lineHeight = 10.sp, textAlign = TextAlign.Center)
            Spacer(modifier = modifier.height(10.dp))
            Box(modifier = modifier
                .size(10.dp)
                .background(color = Background2, shape = RoundedCornerShape(8.dp)))
        }
    } // End of Page Response Block
    Spacer(modifier = modifier.width(5.dp))
}