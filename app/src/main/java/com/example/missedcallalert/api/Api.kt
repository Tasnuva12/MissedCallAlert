package com.example.missedcallalert.api

import com.example.missedcallalert.api.OtpVerifictionAPI.OtpVerificationRequestDataFormat
import com.example.missedcallalert.api.OtpVerifictionAPI.OtpVerificationResponseDataFormat
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST



//It defines the method to send the OTP data to the server
// and get a response back, like checking if the OTP is valid.
public interface Api {
@POST("v1/registration")
suspend fun requestOtp(@Body otpRequest: OtpRequest): Response<OTPResponseDataFormat>
@POST("v1/otp-verification")
suspend fun  verifyOtp(@Body otpVerify:OtpVerificationRequestDataFormat):Response<OtpVerificationResponseDataFormat>
}