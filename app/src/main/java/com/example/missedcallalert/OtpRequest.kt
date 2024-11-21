package com.example.missedcallalert

data class OtpRequest(
    val deviceType: Int,
    val apiName: String,
    val deviceUniqueId: String,
    val username: String,
    val otp: String,
    val fcmToken: String
)
