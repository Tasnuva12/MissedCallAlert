package com.example.missedcallalert

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen
{

    @Serializable
    data object SplashScreen:Screen()
    @Serializable
    data object OtpVerificationScreen:Screen()

    @Serializable
    data object HomeScreen:Screen()

}
