package com.example.githubtrending.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.example.githubtrending.BaseApplication
import com.example.githubtrending.data.remote.api.APIService
import com.example.greenlightplanetassignment.constant.AppConstants
import com.google.gson.GsonBuilder
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


object APIClient {

    private const val TAG = "APIClient"

    // Get API Service Client
    val getClient: APIService
        get() {
            val httpCacheDirectory = File(
                BaseApplication.applicationContext().getCacheDir(),
                "responses"
            )
            val cacheSize = 10 * 1024 * 1024 // 10 MiB

            val cache = Cache(httpCacheDirectory, cacheSize.toLong())

            val httpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(REWRITE_RESPONSE_INTERCEPTOR)
                .addInterceptor(OFFLINE_INTERCEPTOR)
                .cache(cache)
                .build()

            val gson = GsonBuilder()
                .setLenient()
                .create()
            val retrofit = Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(APIService::class.java)

        }

    private val REWRITE_RESPONSE_INTERCEPTOR: Interceptor = Interceptor { chain ->
        val originalResponse: Response = chain.proceed(chain.request())
        val cacheControl: String? = originalResponse.header("Cache-Control")
        if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
            cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")
        ) {
            return@Interceptor originalResponse.newBuilder()
                .header("Cache-Control", "public, max-age=" + 10)
                .build()
        } else {
            return@Interceptor originalResponse
        }
    }

    private val OFFLINE_INTERCEPTOR: Interceptor = Interceptor { chain ->
        var request: Request = chain.request()
        if (!isOnline()) {
            Log.d(TAG, "rewriting request")
            val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
            request = request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .build()
        }
        chain.proceed(request)
    }

    private fun isOnline(): Boolean {
        val cm = BaseApplication.applicationContext()
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

}