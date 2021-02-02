package com.example.githubtrending.helper

import java.util.concurrent.TimeUnit


object AppConstants {

    const val BASE_URL = "https://api.github.com/"
    const val WORK_ID = "SYNC_GIT_TREND_WORK_ID"
    const val DEFAULT_WORK_TIME : Long = 15
    private val mDefaultWorkTime : DefaultWorkTime = DefaultWorkTime.M

    enum class DefaultWorkTime {
        M, S, H, D
    }

    val timeUnit =  when (mDefaultWorkTime) {
        DefaultWorkTime.D -> TimeUnit.DAYS
        DefaultWorkTime.H -> TimeUnit.HOURS
        DefaultWorkTime.M -> TimeUnit.MINUTES
        DefaultWorkTime.S -> TimeUnit.SECONDS
    }

}