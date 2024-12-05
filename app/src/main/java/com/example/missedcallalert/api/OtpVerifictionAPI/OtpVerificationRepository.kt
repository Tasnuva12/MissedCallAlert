package com.example.missedcallalert.api.OtpVerifictionAPI

import com.example.missedcallalert.api.Api
import retrofit2.Response
import javax.inject.Inject

class OtpVerificationRepository  @Inject constructor(
    private val api:Api
){
    suspend fun otpVerificationFunction(
        otp:String,
        username:String
    ):Result<OtpVerificationResponseDataFormat>{
       val request=OtpVerificationRequestDataFormat(
           deviceType = 1,
           apiName = "otp-verification",
           appVersionCode = 1,  // Adjust if needed
           deviceUniqueId = "sifat404040@gmail.com",
           username = username,
           otp = otp,
           fcmToken = "abcd1234efgh5678"
       )
        return try {
            // Perform the API call synchronously with suspend function
            val response = api.verifyOtp(request) // 'verifyOtp' should be a suspend function in OtpApi

            // Check if the response is successful
            if (response.isSuccessful) {
                // Return the successful result
                Result.success(response.body() ?: throw Exception("Response body is null"))
            } else {
                // Handle failure (non-2xx status code)
                Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            // Handle any other exceptions (e.g., network failure)
            Result.failure(e)
        }

    }
}