package com.example.missedcallalert.api.OtpVerifictionAPI

import com.example.missedcallalert.BaseRequest
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OtpVerificationRequest(
    @SerialName("otp") val otp: String,
    @SerialName("fcmToken") val fcmToken: String
) : BaseRequest()