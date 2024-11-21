package com.example.missedcallalert

data class OTPResponseDataFormat(
    val apiName: String,
    val `data`: Data,
    val errorCode: Int,
    val errorMessage: String,
    val status: Int
)