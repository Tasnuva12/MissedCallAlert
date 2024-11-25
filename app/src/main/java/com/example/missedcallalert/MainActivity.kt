package com.example.missedcallalert
import android.window.SplashScreen

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Scaffold

import androidx.compose.ui.Modifier


import com.example.missedcallalert.ui.Screens.SplashScreen
import com.example.missedcallalert.ui.theme.MissedCallAlertTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MissedCallAlertTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SplashScreen(

                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}




