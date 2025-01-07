package com.example.missedcallalert.api.LogInAPI

import com.example.missedcallalert.BaseRequest
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class LoginRequest(
    @SerialName("password") val password: String,
    @SerialName("userId") val userId: String,
    @SerialName("userName") val userName: String,
    @SerialName("fcmToken") val fcmToken: String
) : BaseRequest()