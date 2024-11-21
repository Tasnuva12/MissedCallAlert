package com.example.missedcallalert

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance{
    private const val base_URL="http://192.168.1.63:8086/v1/"
    val retrofit: Retrofit by  lazy {
        Retrofit.Builder()
            .baseUrl(base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}
