package com.example.missedcallalert

data class Data(
    val isPremium: Int,
    val isVerified: Int,
    val password: String,
    val sim1MaskingNumber: Any,
    val sim1Number: Any,
    val sim2MaskingNumber: Any,
    val sim2Number: Any,
    val subscriberProfile: SubscriberProfile,
    val systemDateTime: String,
    val userId: Int,
    val userName: String
)