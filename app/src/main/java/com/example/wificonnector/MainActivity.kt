package com.example.wificonnector

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.view.View
import android.net.wifi.WifiManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private val pageTitle: TextView by lazy {
        findViewById(R.id.pageTitle)
    }

    private val switchWifi: Switch by lazy {
        findViewById(R.id.switchWifi)
    }

    private val showResultButton: Button by lazy {
        findViewById(R.id.showResultButton)
    }

    private val wifiConnectResult: TextView by lazy {
        findViewById(R.id.wifiConnectResult)
    }

    private lateinit var wifiManager: WifiManager
    private val isChecked: Boolean = true

//    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
//        override fun onAvailable(network: Network) {
//            super.onAvailable(network)
//            Log.d("Test", "Wifi Available")
//        }
//
//        override fun onLost(network: Network) {
//            super.onLost(network)
//            Log.d("Test", "Wifi UnAvailable")
//        }
//    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

//        if(wifiManager.isWifiEnabled) {
//            showResultButton.text = "Turn Wifi Off"
//        } else if(!(wifiManager.isWifiEnabled)){
//            showResultButton.text = "Turn Wifi On"
//        }

        /* Original Code */
        switchWifi.setOnClickListener {
            if(wifiManager.isWifiEnabled){
                showResultButton.text = "Turn WiFi On"
                wifiManager.isWifiEnabled = false
            } else if(!(wifiManager.isWifiEnabled)){
                showResultButton.text = "Turn WiFi Off"
                wifiManager.isWifiEnabled = true
            }
        }

//        switchWifi.setOnCheckedChangeListener { _, _ ->
//            if (isChecked) {
//                wifiConnectResult.text = "WIFI ON"
//                enableWiFi()
//            } else {
//                wifiConnectResult.text = "WIFI OFF"
//                disableWiFi()
//            }
//        }

//        switchWifi.setOnCheckedChangeListener { _, _ ->
////            if (switchWifi.isChecked) {
////                wifiConnectResult.text = "WIFI ON"
////                // registerNetworkCallback()
//////                wifiManager =
//////                    applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
//////                wifiManager.isWifiEnabled = false
////                isWIFIConnected(applicationContext)
////
////            } else {
////                wifiConnectResult.text = "WIFI OFF"
////                // unregisterNetworkCallback()
////                connectivityWIFI()
////            }
////        }
//
        showResultButton.setOnClickListener {
            when (NetworkStatus().getConnectivityStatus(applicationContext)) {
                NetworkStatus().typeMobile -> {
                    wifiConnectResult.text = "Connect as Mobile"
                }
                NetworkStatus().typeWifi -> {
                    wifiConnectResult.text = "Connect as Wi-Fi"
                }
                else -> {
                    wifiConnectResult.text = "Could not connect"
                }
            }
        }
    }
//
//    private fun connectivityWIFI() {
//        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
//
//        val builder = NetworkRequest.Builder()
//        builder.addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
//
//        val networkRequest = builder.build()
//        connectivityManager.registerNetworkCallback(networkRequest,
//            object : ConnectivityManager.NetworkCallback() {
//                override fun onAvailable(network: Network) {
//                    super.onAvailable(network)
//                    Log.i("Test", "Network Available")
//                }
//
//                override fun onLost(network: Network) {
//                    super.onLost(network)
//                    Log.i("Test", "Connection lost")
//                }
//            })
//    }

//    @RequiresApi(Build.VERSION_CODES.M)
//    private fun isWIFIConnected(context: Context): Boolean {
//        var result = false
//        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE)
//                as ConnectivityManager
//        val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
//        if (capabilities != null) {
//            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
//                result = true
//            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
//                result = false
//            }
//        }
//        return result
//    }


    private fun disableWiFi() {
        wifiManager =
            applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        wifiManager.isWifiEnabled = false
        Toast.makeText(this, "Wifi Disabled", Toast.LENGTH_SHORT).show()
    }

    private fun enableWiFi() {
        wifiManager =
            applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        wifiManager.isWifiEnabled = true
        Toast.makeText(this, "Wifi Enabled", Toast.LENGTH_SHORT).show()
    }

//    @RequiresApi(Build.VERSION_CODES.M)
//    private fun registerNetworkCallback() {
//        val cm = getSystemService(ConnectivityManager::class.java)
//        val wifiNetworkRequest = NetworkRequest.Builder()
//            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
//            .build()
//
//        cm.registerNetworkCallback(wifiNetworkRequest, networkCallback)
//    }
//
//    @RequiresApi(Build.VERSION_CODES.M)
//    private fun unregisterNetworkCallback() {
//        val cm = getSystemService(ConnectivityManager::class.java)
//        cm.unregisterNetworkCallback(networkCallback)
//    }
//
//    @RequiresApi(Build.VERSION_CODES.M)
//    override fun onResume() {
//        super.onResume()
//        registerNetworkCallback()
//    }
//
//    @RequiresApi(Build.VERSION_CODES.M)
//    override fun onStop() {
//        super.onStop()
//        unregisterNetworkCallback()
//    }
}