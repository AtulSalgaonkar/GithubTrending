package com.example.githubtrending.data.repository

import android.content.Context
import com.example.githubtrending.BaseApplication
import com.example.githubtrending.data.local.dao.GithubTrendingDao
import com.example.githubtrending.data.local.db.AppDatabase
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

    // Get github trending list based on location
    fun getGitHubTrendingDetails(): Single<Result<ResponseModel>> {
        val db: AppDatabase = AppDatabase.getDatabase(applicationContext)
        val githubTrendingDao = db.githubTrendingDao()
        return Single.create { emitter ->
            val apiCall: Call<ResponseModel> = apiService.getGitHubTrendingData()
            apiCall.enqueue(object : Callback<ResponseModel> {
                override fun onResponse(
                    call: Call<ResponseModel>,
                    response: Response<ResponseModel>
                ) {
                    when (response.code()) {
                        200 -> {
                            val responseModel: ResponseModel? = response.body()
                            responseModel?.items?.let { repos ->
                                Single.create<Unit> {
                                    githubTrendingDao.nukeItemTable()
                                    githubTrendingDao.nukeOwnerTable()
                                    repos.forEach { repo ->
                                        repo.owner?.let {
                                            val insertOwnerId: Int =
                                                githubTrendingDao.insertOwner(it).toInt()
                                            repo.ownerUuid = insertOwnerId
                                            repo.let { githubTrendingDao.insertItem(it) }
                                        }
                                    }
                                    emitter.onSuccess(Result.Success(responseModel))
                                }.subscribeOn(Schedulers.io())
                                    .subscribe()
                            }

                            if (responseModel?.items == null || responseModel.items!!.size == 0) {
                                emitter.onSuccess(Result.Error(Exception("Error please try again!")))
                            }
                        }
                        else -> {
                            getLocalData(githubTrendingDao) { responseModelLocal ->
                                if (responseModelLocal != null) {
                                    emitter.onSuccess(Result.Success(responseModelLocal))
                                } else {
                                    val errorJsonString = response.errorBody()?.string()
                                    emitter.onSuccess(Result.Error(Exception(errorJsonString)))
                                }
                            }
                        }
                    }

                }

                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    getLocalData(githubTrendingDao) { responseModelLocal ->
                        if (responseModelLocal != null) {
                            emitter.onSuccess(Result.Success(responseModelLocal))
                        } else {
                            emitter.onSuccess(Result.Error(Exception(t.message)))
                        }
                    }
                }
            })
        }
    }

    // Get github trending list based on location from local
    fun getGitHubTrendingDetailsLocal(): Single<Result<ResponseModel>> {
        val db: AppDatabase = AppDatabase.getDatabase(applicationContext)
        val githubTrendingDao = db.githubTrendingDao()
        return Single.create { emitter ->
            getLocalData(githubTrendingDao) { responseModelLocal ->
                if (responseModelLocal != null) {
                    emitter.onSuccess(Result.Success(responseModelLocal))
                } else {
                    emitter.onSuccess(Result.Error(Exception("Error please try again!")))
                }
            }
        }
    }

    // get offline data
    private fun getLocalData(
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
    }
}