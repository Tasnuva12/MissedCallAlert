package com.example.missedcallalert

import com.example.missedcallalert.api.OtpApi

object APIServiceGenerator {
    val apiService: OtpApi by lazy {
        RetrofitInstance.retrofit.create(OtpApi::class.java)
    }
}