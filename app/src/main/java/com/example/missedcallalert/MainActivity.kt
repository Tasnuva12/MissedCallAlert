package com.example.missedcallalert

import android.content.Intent
import android.os.Build
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.missedcallalert.navigation.NavGraph


import com.example.missedcallalert.ui.theme.MissedCallAlertTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var  shouldRequestPermissions =true
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the permission launcher
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            // Check if all permissions are granted
            val allGranted = permissions.all { it.value }
            shouldRequestPermissions = !allGranted
        }

        // Request permissions if needed
        requestPermissionsIfNeeded()



        enableEdgeToEdge()
        setContent {
            MissedCallAlertTheme {
                Scaffold(modifier = Modifier.fillMaxSize().imePadding()) { innerPadding ->
                   LaunchedEffect(shouldRequestPermissions) {
                       if(shouldRequestPermissions){
                           requestPermissionsIfNeeded()
                       }

                   }
                    NavGraph(navController= rememberNavController(), paddingValues = innerPadding)
                    // Handle intent flags
                    intent?.let {
                        if (it.flags and Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY == 0) {

                               NavGraph(
                                   navController = rememberNavController(),
                                   paddingValues = innerPadding
                               )

                        }
                    }
                }
            }
            enableEdgeToEdge()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun requestPermissionsIfNeeded() {
       val permissions= mutableSetOf(
           android.Manifest.permission.READ_PHONE_STATE,
           android.Manifest.permission.READ_PHONE_NUMBERS,
           android.Manifest.permission.READ_SMS,
           android.Manifest.permission.READ_CONTACTS,
           android.Manifest.permission.POST_NOTIFICATIONS

       )

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){
            permissionLauncher.launch(permissions.toTypedArray())
        }

    }
}




