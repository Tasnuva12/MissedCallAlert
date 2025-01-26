package com.example.missedcallalert.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.missedcallalert.Screen
import com.example.missedcallalert.ui.Screens.HomeScreen
import com.example.missedcallalert.ui.Screens.OtpVerificationScreen
import com.example.missedcallalert.ui.Screens.SplashScreen
import com.example.missedcallalert.viewModels.SplashScreenViewModel

@Composable
fun NavGraph(navController: NavHostController, paddingValues: PaddingValues) {

    //Create NavController



    //Navigation graph set up
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(
                navController,
                modifier = Modifier.padding(paddingValues)
            )
        }
        composable(Screen.OtpVerificationScreen.route) {
            OtpVerificationScreen(
                navController = navController,
                modifier = Modifier.padding(paddingValues),

            )
        }
        
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController = navController, modifier = Modifier.padding(paddingValues))
        }
    }
}
