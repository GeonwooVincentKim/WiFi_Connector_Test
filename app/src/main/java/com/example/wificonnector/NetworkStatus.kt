package com.example.wificonnector

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService

class NetworkStatus {
    val typeWifi: Int = 1
    val typeMobile: Int = 2
    val typeNotConnected: Int = 3

    @RequiresApi(Build.VERSION_CODES.M)
    fun getConnectivityStatus(context: Context): Int {
        var result = 0
        val manager: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val networkInfo: NetworkInfo = manager.activeNetworkInfo!!
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        cm.run {
            cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                if (hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    result = typeWifi
                } else if (hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    result = typeMobile
                } else {
                    result = typeNotConnected
                }
            }
        }

        return result
    }
}