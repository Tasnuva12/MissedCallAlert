package com.example.missedcallalert

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.util.component1
import androidx.core.util.component2

@Composable
fun NetworkMonitor(onNetworkChange: @Composable (Boolean)->Unit) {
    val context = LocalContext.current
    val (isWifiActive, isMobileDataActive) = context.NetworkStatus()
    val isInternetConnected =isWifiActive || isMobileDataActive

    onNetworkChange(isInternetConnected )



}