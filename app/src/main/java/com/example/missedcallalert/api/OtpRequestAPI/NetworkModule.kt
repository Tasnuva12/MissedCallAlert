package com.example.missedcallalert.api.OtpRequestAPI

import com.example.missedcallalert.api.OtpApi
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton
//creates and provides the OtpApi so that
// the app can send OTP requests to the server using Retrofit.
object NetworkModule {
    @Provides
    @Singleton
    fun provideOtpApi(retrofit: Retrofit):OtpApi{
       return retrofit.create(OtpApi::class.java)
    }
}