package com.example.githubtrending.helper

import android.content.Context
import androidx.work.*
import com.example.githubtrending.work.GithubTrendingWorker
import java.util.concurrent.TimeUnit

class Helper {

    companion object {

        // instantiate work manager
        fun initiateWorkManager(context: Context) {
            val workManager = WorkManager.getInstance(context)

            workManager.cancelAllWorkByTag(AppConstants.WORK_ID)

            val constraint = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val timeUnit: TimeUnit = AppConstants.timeUnit

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

}