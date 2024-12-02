package com.example.missedcallalert.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST



//It defines the method to send the OTP data to the server
// and get a response back, like checking if the OTP is valid.
public interface OtpApi {
@POST("v1/registration")
suspend fun requestOtp(@Body otpRequest: OtpRequest): Response<OTPResponseDataFormat>
}