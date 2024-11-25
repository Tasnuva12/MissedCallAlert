package com.example.missedcallalert.api

data class OtpRequest(
    val username: String,
    val deviceType: Int,
    val deviceUniqueId: String,

    val apiName: String,
    val appVersionCode: Int
)
