package com.example.missedcallalert

import kotlinx.serialization.Serializable

@Serializable
data class AppConfigurationResponse(
    val data: Data?,
): BaseResponse() {
    @Serializable
    data class Data(
        val aboutUs: String?,
        val privacyPolicy: String?,
        val termsConditions: String?,
        val ussdCode: UssdCode?
    ) {
        @Serializable
        data class UssdCode(
            val airtel: Carrier?,
            val banglalink: Carrier?,
            val gp: Carrier?,
            val robi: Carrier?,
            val teletalk: Carrier?
        )
    }
}

@Serializable
data class Carrier(
    val allCalls: String?,
    val callForwardingStatus: String?,
    val cancelCallForwarding: String?,
    val notAnswered: String?,
    val notReachable: String?
)