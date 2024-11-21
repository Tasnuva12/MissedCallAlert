package com.example.missedcallalert

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

public interface OtpApi {
@POST("otp-verification")
fun verifyOtp(@Body otpRequest: OtpRequest): Call<OTPResponseDataFormat>
}