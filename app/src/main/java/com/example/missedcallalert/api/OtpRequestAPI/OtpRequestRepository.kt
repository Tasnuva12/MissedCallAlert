package com.example.missedcallalert.api

import android.net.nsd.NsdManager.ResolveListener
import android.util.Log
import com.example.missedcallalert.data.Country
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class OtpRequestRepository @Inject constructor(
    private val api: OtpApi
){
    suspend fun otpRequestFunction(phoneNo: String, selectedCode: Country): Result<OTPResponseDataFormat> {
        val request = OtpRequest(
            username = "${selectedCode.code}$phoneNo",
            deviceType = 1,
            deviceUniqueId = "sifat404040@gmail.com",
            apiName = "registration",
            appVersionCode = 1
        )

        return try {
            val response = api.verifyOtp(request)
            if (response.isSuccessful) {
                Result.success(response.body() ?: throw Exception("Response body is null"))
            } else {
                Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    





//        call.enqueue(object : Callback<OTPResponseDataFormat> {
//            override fun onResponse(
//                call: Call<OTPResponseDataFormat>,
//                response: Response<OTPResponseDataFormat>
//            ) {
//                Log.d("RawResponse", response.toString())
//                if (response.isSuccessful) {
//                    val responseBody = response.body()
//                    if (responseBody != null) {
//                        println("OTP Verification Successful: ${responseBody.status}")
//                        Log.d("Otp","Successful")
//
//                    } else {
//
//                        println("Response body is null")
//                        Log.d("Otp","Not Successful")
//
//
//
//                    }
//                } else {
//
//                    println("Error: ${response.code()} - ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<OTPResponseDataFormat>, t: Throwable) {
//
//                println("Network Error: ${t.message}")
//            }
//        })





    }
}
