package com.example.githubtrending.helper

import java.util.concurrent.TimeUnit


object AppConstants {

    // base url of api call
    const val BASE_URL = "https://api.github.com/"

    // default worker id
    const val WORK_ID = "SYNC_GIT_TREND_WORK_ID"

    // constant for work manager periodic call
    const val DEFAULT_WORK_TIME : Long = 15

    private val mDefaultWorkTime : DefaultWorkTime = DefaultWorkTime.M

    enum class DefaultWorkTime {
        M, S, H, D
    }

    // constant for work manager TimeUnit
    val timeUnit =  when (mDefaultWorkTime) {
        DefaultWorkTime.D -> TimeUnit.DAYS
        DefaultWorkTime.H -> TimeUnit.HOURS
        DefaultWorkTime.M -> TimeUnit.MINUTES
        DefaultWorkTime.S -> TimeUnit.SECONDS
    }

}