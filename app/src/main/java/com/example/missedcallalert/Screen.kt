package com.example.missedcallalert

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object SplashScreen : Screen() {
        val route = "splash_screen"
    }

    @Serializable
    data object OtpVerificationScreen : Screen() {
        val route = "otp_verification_screen"
    }

    @Serializable
    data object HomeScreen : Screen() {
        val route = "home_screen"
    }
}
