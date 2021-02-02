package com.example.githubtrending

import android.app.Application
import android.content.Context
import androidx.work.*
import com.example.githubtrending.work.GithubTrendingWorker
import com.example.greenlightplanetassignment.constant.AppConstants
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

        val workManager = WorkManager.getInstance(this)

        workManager.cancelAllWorkByTag(AppConstants.WORK_ID)

        val constraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val timeUnit: TimeUnit = AppConstants.getDefaultTimeUnit()

        val workRequest = PeriodicWorkRequest.Builder(
            GithubTrendingWorker::class.java,
            AppConstants.DEFAULT_WORK_TIME,
            timeUnit
        ).setConstraints(constraint)
            .setInitialDelay(AppConstants.DEFAULT_WORK_TIME, timeUnit)
            .addTag(AppConstants.WORK_ID)
            .build()

        workManager.enqueueUniquePeriodicWork(
            AppConstants.WORK_ID,
            ExistingPeriodicWorkPolicy.REPLACE,
            workRequest
        )

    }
}