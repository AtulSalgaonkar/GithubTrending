package com.example.githubtrending

import android.app.Application
import android.content.Context
import androidx.work.*
import com.example.githubtrending.work.GithubTrendingWorker
import com.example.githubtrending.helper.AppConstants
import com.example.githubtrending.helper.Helper
import java.util.concurrent.TimeUnit


class BaseApplication : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: BaseApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        Helper.initiateWorkManager(this)

    }
}