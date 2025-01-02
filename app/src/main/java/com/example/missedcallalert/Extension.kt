package com.example.missedcallalert



import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build.VERSION_CODES

import android.util.Pair
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


private const val PHONE_PATTERN = "^(?:\\+8801|8801|01)(\\d{9})$"

fun String.isValid(type: InputType): Boolean{
    return when(type){
       InputType.PHONE->this.isNotBlank() and PHONE_PATTERN.toRegex().matches(this)

    }
}

@RequiresApi(VERSION_CODES.N)
@Composable
fun Context.NetworkStatus(): Pair<Boolean, Boolean> {
    val connectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    var isWifiActive by remember { mutableStateOf(false) }
    var isMobileDataActive by remember { mutableStateOf(false) }



    fun checkNetworkStatus(networkCapabilities: NetworkCapabilities?) {
        if (networkCapabilities != null) {
            isWifiActive = networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            isMobileDataActive =
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        } else {
            isWifiActive = false
            isMobileDataActive = false
        }
    }

    LaunchedEffect(Unit) {
        // Register network callback
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                val networkCapabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                checkNetworkStatus(networkCapabilities)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                checkNetworkStatus(null)
            }
        }
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }
    return Pair(isWifiActive, isMobileDataActive)

}
