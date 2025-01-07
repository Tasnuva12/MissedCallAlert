package com.example.missedcallalert.api.LogInAPI




import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val apiName: String?,
    val data: Data?,
    val errorCode: Int?,
    val errorMessage: String?,
    val status: Int?
) {
    @Serializable
    data class Data(
        val isPremium: Int?,
        val password: String?,
        val sim1MaskingNumber: String?,
        val sim1Number: String?,
        val sim2MaskingNumber: String?,
        val sim2Number: String?,
        val subscriberProfile: SubscriberProfile?,
        val userId: Int?,
        val userName: String?
    ) {
        @Serializable
        data class SubscriberProfile(
            val address: String?,
            val name: String?,
            val userName: String?
        )
    }
}