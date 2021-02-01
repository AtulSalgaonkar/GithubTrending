package com.example.githubtrending.data.repository

import com.example.githubtrending.data.model.ResponseModel
import com.example.githubtrending.data.model.Result
import io.reactivex.Single

class GitHubRepoRepository(var remoteDataSource: RemoteDataSource) {

    fun getGitHubTrendingDetails(): Single<Result<ResponseModel>> {
        return remoteDataSource.getGitHubTrendingDetails()
    }

}