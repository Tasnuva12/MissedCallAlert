package com.example.missedcallalert

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import android. app. Activity

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize your app here if needed
    }
}
