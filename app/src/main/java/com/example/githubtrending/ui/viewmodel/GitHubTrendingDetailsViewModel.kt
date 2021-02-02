package com.example.githubtrending.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubtrending.data.model.Item
import com.example.githubtrending.data.model.ResponseModel
import com.example.githubtrending.data.repository.GitHubRepoRepository
import com.example.githubtrending.data.repository.GithHubRepoDataSource
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import com.example.githubtrending.data.model.Result

class GitHubTrendingDetailsViewModel : ViewModel() {

    private var repository: GitHubRepoRepository = GitHubRepoRepository(GithHubRepoDataSource())

    // api response live data
    val gitHubTrendingLiveData: MutableLiveData<Result<ArrayList<Item>>> = MutableLiveData()

    // local response live data
    val gitHubTrendingLocalLiveData: MutableLiveData<Result<ArrayList<Item>>> = MutableLiveData()

    // Get get trending repository data from api
    fun getTrendingAndroidReposList() {
        getGitHubTrendingDetails({}, { result ->
            if (result is Result.Success) {
                val itemData = result.data?.items
                gitHubTrendingLiveData.value = Result.Success(itemData)
            } else if (result is Result.Error) {
                gitHubTrendingLiveData.value = result
            }
        }, { e ->
            gitHubTrendingLiveData.value = Result.Error(Exception(e.message))
        })
    }

    // Get get trending repository data from local
    fun getTrendingAndroidReposListLocal() {
        getGitHubTrendingDetailsLocal({}, { result ->
            if (result is Result.Success) {
                val itemData = result.data?.items
                gitHubTrendingLocalLiveData.value = Result.Success(itemData)
            } else if (result is Result.Error) {
                gitHubTrendingLocalLiveData.value = result
            }
        }, { e ->
            gitHubTrendingLocalLiveData.value = Result.Error(Exception(e.message))
        })
    }

    /**
     * Common function for getting Github repositories list
     * @param onSuccessDetails -> success result from api call
     * @param onErrorDetails -> returns error from api call
     */
    private fun getGitHubTrendingDetails(
        onSubscribeDetails: (Disposable) -> Unit,
        onSuccessDetails: (Result<ResponseModel>) -> Unit,
        onErrorDetails: (Throwable) -> Unit
    ) {
        repository.getGitHubTrendingDetails()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Result<ResponseModel>> {
                override fun onSubscribe(d: Disposable) {
                    onSubscribeDetails(d)
                }

                override fun onSuccess(result: Result<ResponseModel>) {
                    onSuccessDetails(result)
                }

                override fun onError(e: Throwable) {
                    onErrorDetails(e)
                }
            })
    }

    /**
     * Common function for getting Github repositories list from local
     * @param onSuccessDetails -> success result from local
     * @param onErrorDetails -> returns error from local
     */
    private fun getGitHubTrendingDetailsLocal(
        onSubscribeDetails: (Disposable) -> Unit,
        onSuccessDetails: (Result<ResponseModel>) -> Unit,
        onErrorDetails: (Throwable) -> Unit
    ) {
        repository.getGitHubTrendingDetailsLocal()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Result<ResponseModel>> {
                override fun onSubscribe(d: Disposable) {
                    onSubscribeDetails(d)
                }

                override fun onSuccess(result: Result<ResponseModel>) {
                    onSuccessDetails(result)
                }

                override fun onError(e: Throwable) {
                    onErrorDetails(e)
                }
            })
    }
}