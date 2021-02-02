package com.example.githubtrending.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import com.example.githubtrending.helper.Helper

// Boot Completed receiver for set work after restart of device
class OnBootCompleted : BroadcastReceiver() {

    private val TAG = "OnBootCompleted"

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null) {
            val action: String? = intent.action
            action?.let {
                if (action == "android.intent.action.BOOT_COMPLETED") {
                    context?.let { c -> Helper.initiateWorkManager(c) }
                }
            }
        }
    }
}