package com.example.missedcallalert.api.OtpVerifictionAPI

data class OtpVerificationResponseDataFormat(
    val status: Int,
    val apiName: String,
    val data: ResponseData?,
    val errorCode: Int,
    val errorMessage: String
)
data class ResponseData(
    val isVerified: Int,
    val userName: String,
    val password: String,
    val userId: Int,
    val sim1Number: String?,
    val sim2Number: String?,
    val sim1MaskingNumber: String?,
    val sim2MaskingNumber: String?,
    val isPremium: Int,
    val subscriberProfile: SubscriberProfile?,
    val systemDateTime: String
)

data class SubscriberProfile(
    val address: String,
    val name: String,
    val userName: String
)

