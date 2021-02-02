package com.example.githubtrending.work

import android.content.Context
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.work.RxWorker
import androidx.work.WorkerParameters
import com.example.githubtrending.data.model.ResponseModel
import com.example.githubtrending.data.repository.GitHubRepoRepository
import com.example.githubtrending.data.repository.RemoteDataSource
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class GithubTrendingWorker(
    context: Context,
    workerParameters: WorkerParameters
) :
    RxWorker(context, workerParameters) {

    private var mContext: Context? = null

    init {
        mContext = context
    }

    override fun createWork(): Single<Result> {
        val repository = GitHubRepoRepository(RemoteDataSource())

        val localBroadcastManager = mContext?.let { LocalBroadcastManager.getInstance(it) }

        return Single.create { observer ->
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
                            localBroadcastManager?.sendBroadcast(intent)
                        }
                        observer.onSuccess(Result.success())
                    }

                    override fun onError(e: Throwable) {
                        observer.onSuccess(Result.success())
                    }
                })
        }
    }


}