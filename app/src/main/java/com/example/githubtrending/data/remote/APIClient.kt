package com.example.githubtrending.data.remote

import com.example.githubtrending.data.remote.api.APIService
import com.example.githubtrending.helper.AppConstants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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