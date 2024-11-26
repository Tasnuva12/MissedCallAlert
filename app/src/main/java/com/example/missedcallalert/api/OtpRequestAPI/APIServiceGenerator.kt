package com.example.missedcallalert.api.OtpRequestAPI


import com.example.missedcallalert.api.OtpApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)  // Makes it available application-wide
object NetworkModule {

    @Provides
    @Singleton
    fun APIServiceGenerator(retrofit: Retrofit): OtpApi {
        return retrofit.create(OtpApi::class.java)
    }
}
