package com.example.missedcallalert.api

import android.net.nsd.NsdManager.ResolveListener
import android.util.Log
import com.example.missedcallalert.data.Country
import retrofit2.Call
import retrofit2.Callback


import javax.inject.Inject

class OtpRequestRepository @Inject constructor(
    private val api: OtpApi
) {
    // Use suspend function to handle network requests asynchronously
    suspend fun otpRequestFunction(phoneNo: String, selectedCode: Country): Result<OTPResponseDataFormat> {
        // Create the request data
        val request = OtpRequest(
            username = "${selectedCode.code}$phoneNo",
            deviceType = 1,
            deviceUniqueId = "sifat404040@gmail.com", // ideally, fetch this dynamically
            apiName = "registration",
            appVersionCode = 1
        )

        return try {
            // Perform the API call synchronously with suspend function
            val response = api.requestOtp(request) // 'verifyOtp' should be a suspend function in OtpApi

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
