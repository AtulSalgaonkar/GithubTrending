package com.example.githubtrending.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import com.example.githubtrending.helper.Helper

class OnBootCompleted : BroadcastReceiver() {

    private val TAG = "OnBootCompleted"

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null) {
            val action: String? = intent.action
            action?.let {
                Log.e(TAG, "onReceive: $action")
                if (action == "android.intent.action.BOOT_COMPLETED") {
                    Log.e(TAG, "onReceive: BOOT_COMPLETED")
                    context?.let { c -> Helper.initiateWorkManager(c) }
                }
            }
        }
    }
}