package com.example.missedcallalert.api.OtpVerifictionAPI

data class OtpVerificationRequestDataFormat(
    val deviceType: Int,
    val apiName: String,
    val appVersionCode: Int,
    val deviceUniqueId: String,
    val username: String,
    val otp: String,
    val fcmToken: String
)
