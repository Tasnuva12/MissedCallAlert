package com.example.missedcallalert.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton
//creates and provides the OtpApi so that
// the app can send OTP requests to the server using Retrofit.
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOtpApi(retrofit: Retrofit):Api{
       return retrofit.create(Api::class.java)
    }

}