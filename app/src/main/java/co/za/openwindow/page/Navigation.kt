package co.za.openwindow.classworkapplication

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.za.openwindow.page.screens.AllChatsScreen

import co.za.openwindow.page.screens.LoginScreen
import co.za.openwindow.page.screens.RegisterScreen
import co.za.openwindow.page.screens.ChatScreen
import co.za.openwindow.page.viewmodels.AuthViewModel


//DEFINE ALL MY NAV LINKS
object AuthRoutes { //if user is not logged in
    const val loginScreen = "login"
    const val registerScreen = "register"
}

object HomeRoutes { //when user has logged in
    const val chatScreen = "chat"
    const val allChatsScreen = "all chats"
//    TODO: Other screens
}


// Manage all Navigation
@Composable
fun Navigation(
    authViewModel: AuthViewModel,
    navController : NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){


    //TODO: Add functionality to check which route to start with

    val startingScreen = if (authViewModel.userState){
        HomeRoutes.allChatsScreen
    } else {
        AuthRoutes.loginScreen
    }

    //Router - where we define all of our composable nav routes
    NavHost(navController = navController, startDestination =startingScreen){

        // DEFINE ALL OUT SCREENS THAT CAN BE NAVIGATED TO

        //Auth Screens
        composable(route = AuthRoutes.loginScreen){
            LoginScreen(
                navigateToRegister = {
                    navController.navigate(AuthRoutes.registerScreen)
                },
                navigateToHome = {
                    navController.navigate(HomeRoutes.allChatsScreen){
                        launchSingleTop = true
                        popUpTo(route = AuthRoutes.loginScreen){
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = AuthRoutes.registerScreen){
            RegisterScreen(
                navigateToLogin = {
                    navController.navigate(AuthRoutes.loginScreen)
                }
            )
        }




        //Home Screens
        composable(route = HomeRoutes.chatScreen){
            ChatScreen()
        }

        composable(route = HomeRoutes.allChatsScreen){
            AllChatsScreen(
                navigateToChat = {
                    navController.navigate(HomeRoutes.chatScreen)
                }
            )
        }

    }
}

//@Preview here