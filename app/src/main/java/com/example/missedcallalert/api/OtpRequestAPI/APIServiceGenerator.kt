package com.example.missedcallalert.api.OtpRequestAPI

import com.example.missedcallalert.RetrofitInstance
import com.example.missedcallalert.api.OtpApi

object APIServiceGenerator {
    val apiService: OtpApi by lazy {
        RetrofitInstance.retrofit.create(OtpApi::class.java)
    }
}