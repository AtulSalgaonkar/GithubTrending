package com.example.githubtrending.data.remote.api

import com.example.githubtrending.data.model.Item
import com.example.githubtrending.data.model.ResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("search/repositories")
    fun getGitHubTrendingData(
        @Query("q") q: String = "android",
        @Query("sort") sort: String = "stars",
        @Query("order") order: String = "desc",
        @Query("per_page") per_page: String = "100",
        @Query("page") page: String = "1"
    ): Call<ResponseModel>

}