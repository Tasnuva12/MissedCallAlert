package com.example.missedcallalert.api.LogInAPI

import com.example.missedcallalert.api.Api
import com.example.missedcallalert.data.SessionPreference
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val apiService:Api,
    private val sessionPreference: SessionPreference

) {
suspend fun execute():LoginResponse{
    val response=apiService.login(
        deviceType = sessionPreference.deviceType,
        LoginRequestData(
            password = sessionPreference.password.toString(),
            userId = sessionPreference.userIp,
            userName = sessionPreference.PhoneNumber
        )

    )

}




}