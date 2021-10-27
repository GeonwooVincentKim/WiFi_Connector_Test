package com.example.wificonnector

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import android.os.Build

import android.content.Intent


class AirplaneModeService {
    fun run(context: Context): Boolean {
        val isEnabled = isAirplaneModeOn(context)
        // Toggle airplane mode.
        setSettings(context, if (isEnabled) 1 else 0)
        // Post an intent to reload.
        val intent = Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        intent.putExtra("state", !isEnabled)
        context.sendBroadcast(intent)
        return true
    }

    companion object {
        @SuppressLint("ObsoleteSdkInt")
        fun isAirplaneModeOn(context: Context): Boolean {
            return Settings.System.getInt(
                context.contentResolver,
                Settings.Global.AIRPLANE_MODE_ON, 0
            ) != 0
        }

        @SuppressLint("ObsoleteSdkInt")
        fun setSettings(context: Context, value: Int) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
                Settings.System.putInt(
                    context.contentResolver,
                    Settings.System.AIRPLANE_MODE_ON, value
                )
            } else {
                Settings.Global.putInt(
                    context.contentResolver,
                    Settings.Global.AIRPLANE_MODE_ON, value
                )
            }
        }
    }
}