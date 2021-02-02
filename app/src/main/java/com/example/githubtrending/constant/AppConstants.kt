package com.example.greenlightplanetassignment.constant

import java.util.concurrent.TimeUnit


object AppConstants {

    const val BASE_URL = "https://api.github.com/"
    const val WORK_ID = "SYNC_GIT_TREND_WORK_ID"
    const val DEFAULT_WORK_TIME = 15L
    val mDefaultWorkTime : DefaultWorkTime = DefaultWorkTime.M

    enum class DefaultWorkTime {
        M, S, H, D
    }


    fun getDefaultTimeUnit(): TimeUnit {
        return when (mDefaultWorkTime) {
            DefaultWorkTime.D -> TimeUnit.DAYS
            DefaultWorkTime.H -> TimeUnit.HOURS
            DefaultWorkTime.M -> TimeUnit.MINUTES
            DefaultWorkTime.S -> TimeUnit.SECONDS
        }
    }

}