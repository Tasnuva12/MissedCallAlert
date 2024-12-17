package com.example.missedcallalert.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.missedcallalert.ui.Screens.HomeScreen
import com.example.missedcallalert.ui.Screens.SplashScreen

@Composable
fun NavGraph(){
    //Create NavController
    val navController= rememberNavController()


    //Navigation graph set up
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController=navController)
        }
        composable("home") {
            HomeScreen(navController = navController)
        }
    }
}
