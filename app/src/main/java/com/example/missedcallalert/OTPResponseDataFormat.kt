package com.example.missedcallalert

data class OTPResponseDataFormat(
    val status: Int,
    val apiName: String,
    val data: RegData,
    val errorCode: Int,
    val errorMessage: String
)
data class RegData(
    val msisdnGatewayCode: Int,
    val message: String
)
