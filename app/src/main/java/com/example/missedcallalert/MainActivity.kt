package com.example.missedcallalert

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding

import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Scaffold

import androidx.compose.ui.Modifier
import com.example.missedcallalert.navigation.NavGraph
import com.example.missedcallalert.ui.Screens.HomeScreen


import com.example.missedcallalert.ui.theme.MissedCallAlertTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MissedCallAlertTheme {
                Scaffold(modifier = Modifier.fillMaxSize().imePadding()) { innerPadding ->
                   NavGraph()
                }
            }
        }
    }
}




