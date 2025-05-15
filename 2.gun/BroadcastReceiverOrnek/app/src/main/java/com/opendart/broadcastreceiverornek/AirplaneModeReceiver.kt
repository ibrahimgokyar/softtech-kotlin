package com.opendart.broadcastreceiverornek

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AirplaneModeReceiver (val showState: (state: Boolean) -> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val state = intent?.getBooleanExtra("state", false)
        state?.let { showState(it) }
    }
}