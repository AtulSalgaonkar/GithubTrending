package com.example.githubtrending.data.repository

import android.content.Context
import com.example.githubtrending.BaseApplication
import com.example.githubtrending.R
import com.example.githubtrending.data.model.ResponseModel
import com.example.githubtrending.data.remote.APIClient
import com.example.githubtrending.data.remote.api.APIService
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.githubtrending.data.model.Result

class RemoteDataSource(
    var apiService: APIService = APIClient.getClient,
    var applicationContext: Context = BaseApplication.applicationContext()
) {

    // Get Employees details based on location
    fun getGitHubTrendingDetails(): Single<Result<ResponseModel>> {
        return Single.create { emitter ->
            val apiCall: Call<ResponseModel> = apiService.getGitHubTrendingData(
                "android",
                "stars",
                "desc",
                "100",
                "1"
            )
            apiCall.enqueue(object : Callback<ResponseModel> {
                override fun onResponse(
                    call: Call<ResponseModel>,
                    response: Response<ResponseModel>
                ) {
                    when (response.code()) {
                        200 -> {
                            val responseModel: ResponseModel = response.body() as ResponseModel
                            if (!responseModel.incompleteResults) {
                                /*responseModel.items?.let { data ->
                                    Single.create<Unit> {
                                        githubTrendingDao.nukeItemTable()
                                        data.let { githubTrendingDao.insertItem(it) }
                                    }.subscribeOn(Schedulers.io())
                                        .subscribe()
                                }*/
                                emitter.onSuccess(Result.Success(responseModel))
                            } else {
                                val errorJsonString =
                                    applicationContext.getString(R.string.error_try_again)
                                emitter.onSuccess(Result.Error(Exception(errorJsonString)))
                            }
                        }
                        else -> {
                            val errorJsonString = response.errorBody()?.string()
                            emitter.onSuccess(Result.Error(Exception(errorJsonString)))
                        }
                    }

                }

                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    emitter.onSuccess(Result.Error(Exception(t.message)))
                }
            })
        }
    }

    // get offline data
    /*private fun getLocalData(
        githubTrendingDao: GithubTrendingDao,
        func: (ResponseModel?) -> Unit
    ) {
        Single.create<ResponseModel> {
            val responseModel = ResponseModel()
            val itemData = githubTrendingDao.getItem() as ArrayList
            responseModel.items = itemData
            it.onSuccess(responseModel)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ResponseModel> {
                override fun onSubscribe(d: Disposable) {}
                override fun onSuccess(result: ResponseModel) {
                    func(result)
                }

                override fun onError(e: Throwable) {
                    func(null)
                }
            })
    }*/
}