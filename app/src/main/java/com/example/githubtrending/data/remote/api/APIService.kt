package com.example.githubtrending.data.remote.api

import com.example.githubtrending.data.model.Item
import com.example.githubtrending.data.model.ResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("orgs/android/repos")
    fun getGitHubTrendingData(
        @Query("accept") accept: String,
        @Query("direction") direction: String,
        @Query("per_page") per_page: String,
        @Query("page") page: String
    ): Call<List<Item>>

}