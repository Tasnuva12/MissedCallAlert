package com.example.missedcallalert

object APIServiceGenerator {
    val apiService: OtpApi by lazy {
        RetrofitInstance.retrofit.create(OtpApi::class.java)
    }
}