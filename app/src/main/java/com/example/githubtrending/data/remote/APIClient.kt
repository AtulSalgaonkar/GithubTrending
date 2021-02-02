package com.example.githubtrending.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.example.githubtrending.BaseApplication
import com.example.githubtrending.data.remote.api.APIService
import com.example.greenlightplanetassignment.constant.AppConstants
import com.google.gson.GsonBuilder
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


object APIClient {

    private const val TAG = "APIClient"

    // Get API Service Client
    val getClient: APIService
        get() {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            val retrofit = Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(APIService::class.java)
        }
}