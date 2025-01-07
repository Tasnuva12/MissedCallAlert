package com.example.missedcallalert.api

import com.example.missedcallalert.api.LogInAPI.LoginResponse
import com.example.missedcallalert.api.OtpVerifictionAPI.OtpVerificationRequestDataFormat
import com.example.missedcallalert.api.OtpVerifictionAPI.OtpVerificationResponseDataFormat
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path


//It defines the method to send the OTP data to the server
// and get a response back, like checking if the OTP is valid.
public interface Api {
@POST("v1/registration")
suspend fun requestOtp(@Body otpRequest: OtpRequest): Response<OTPResponseDataFormat>
@POST("v1/otp-verification")
suspend fun  verifyOtp(@Body otpVerify:OtpVerificationRequestDataFormat):Response<OtpVerificationResponseDataFormat>


    @POST("v1/login/{deviceType}")
    suspend fun getLoggedIn(
        @Path("deviceType") deviceType: Int,
        @Body loginRequest: LoginRequestData
    ): LoginResponse
}



