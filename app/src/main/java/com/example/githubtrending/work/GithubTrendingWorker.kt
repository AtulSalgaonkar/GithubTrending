package com.example.githubtrending.work

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.work.RxWorker
import androidx.work.WorkerParameters
import com.example.githubtrending.data.model.ResponseModel
import com.example.githubtrending.data.repository.GitHubRepoRepository
import com.example.githubtrending.data.repository.GithHubRepoDataSource
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

// work manager for sync github trending repository to local db
class GithubTrendingWorker(
    context: Context,
    workerParameters: WorkerParameters
) :
    RxWorker(context, workerParameters) {

    private val TAG = "GithubTrendingWorker"
    private var mContext: Context? = null

    init {
        mContext = context
    }

    override fun createWork(): Single<Result> {
        Log.d(TAG, "createWork: ")
        val repository = GitHubRepoRepository(GithHubRepoDataSource())

        val localBroadcastManager = mContext?.let { LocalBroadcastManager.getInstance(it) }

        return Single.create { observer ->
            Log.d(TAG, "createWork: Single.create")
            repository.getGitHubTrendingDetails()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :
                    SingleObserver<com.example.githubtrending.data.model.Result<ResponseModel>> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onSuccess(result: com.example.githubtrending.data.model.Result<ResponseModel>) {
                        if (result is com.example.githubtrending.data.model.Result.Success) {
                            // if data sync successful then ui update with broadcast receiver
                            val intent = Intent()
                            intent.action = "data_sync_ui_update"
                            Log.d(TAG, "onSuccess: Single onSuccess")
                            localBroadcastManager?.sendBroadcast(intent)
                        }
                        Log.d(TAG, "onSuccess: Single response")
                        observer.onSuccess(Result.success())
                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, "onSuccess: Single onError")
                        observer.onSuccess(Result.success())
                    }
                })
        }
    }


}