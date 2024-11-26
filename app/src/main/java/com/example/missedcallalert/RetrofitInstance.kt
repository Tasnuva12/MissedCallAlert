package com.example.missedcallalert.di


import com.example.missedcallalert.api.OtpRequest

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)  // This makes the module available app-wide
object RetrofitInstance {

    @Provides
    @Singleton  // Retrofit instance will be a singleton
    fun provideRetrofit(): Retrofit {
        val gson: Gson = GsonBuilder().create()
        return Retrofit.Builder()
            .baseUrl("http://103.68.104.21:8086/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }


}
