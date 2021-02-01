package com.example.githubtrending.data.remote.api

import com.example.githubtrending.data.model.ResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    // search/repositories?q=android&sort=stars&order=desc&per_page=100&page=1
    @GET("search/repositories")
    fun getGitHubTrendingData(
        @Query("q") q: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("per_page") per_page: String,
        @Query("page") page: String
    ): Call<ResponseModel>

}