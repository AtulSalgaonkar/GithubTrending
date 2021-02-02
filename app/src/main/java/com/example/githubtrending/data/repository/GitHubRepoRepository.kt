package com.example.githubtrending.data.repository

import com.example.githubtrending.data.model.ResponseModel
import com.example.githubtrending.data.model.Result
import io.reactivex.Single

class GitHubRepoRepository(var githHubRepoDataSource: GithHubRepoDataSource) {

    fun getGitHubTrendingDetails(): Single<Result<ResponseModel>> {
        return githHubRepoDataSource.getGitHubTrendingDetails()
    }

    fun getGitHubTrendingDetailsLocal(): Single<Result<ResponseModel>> {
        return githHubRepoDataSource.getGitHubTrendingDetailsLocal()
    }

}