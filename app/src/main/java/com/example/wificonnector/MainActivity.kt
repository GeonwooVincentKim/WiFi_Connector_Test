package com.example.wificonnector

import android.annotation.SuppressLint
import android.content.Context
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private val pageTitle: TextView by lazy {
        findViewById(R.id.pageTitle)
    }

    private val switchWifi: Switch by lazy {
        findViewById(R.id.switchWifi)
    }

    private val wifiConnectResult: TextView by lazy {
        findViewById(R.id.wifiConnectResult)
    }

    private lateinit var wifiManager: WifiManager
//    private val isChecked: Boolean = true

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        switchWifi.setOnCheckedChangeListener { _, isChecked ->
            if (switchWifi.isChecked) {
                wifiConnectResult.text = "WIFI ON"
                enableWiFi()
            } else {
                wifiConnectResult.text = "WIFI OFF"
                disableWiFi()
            }
        }
    }


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
}