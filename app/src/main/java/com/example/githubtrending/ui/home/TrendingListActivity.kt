package com.example.githubtrending.ui.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.githubtrending.R
import com.example.githubtrending.data.model.Result
import com.example.githubtrending.databinding.TrendingListActivityBinding
import com.example.githubtrending.ui.adapter.RvListAdapter
import com.example.githubtrending.ui.viewmodel.GitHubTrendingDetailsViewModel


class TrendingListActivity : AppCompatActivity() {

    private lateinit var binding: TrendingListActivityBinding
    private lateinit var viewModel: GitHubTrendingDetailsViewModel
    private lateinit var adapter: RvListAdapter
    private val TAG = "HomeActivity"
    private lateinit var localBroadcastManager: LocalBroadcastManager

    // broadcast receiver for refresh ui after new data is synced with local db
    private val mMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            if (intent.action == "data_sync_ui_update") {
                if (!isFinishing) {
                    fetchData(true)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        localBroadcastManager = LocalBroadcastManager.getInstance(this)
        localBroadcastManager.registerReceiver(
            mMessageReceiver,
            IntentFilter("data_sync_ui_update")
        )
    }

    override fun onPause() {
        super.onPause()
        localBroadcastManager.unregisterReceiver(mMessageReceiver)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TrendingListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(GitHubTrendingDetailsViewModel::class.java)

        initRecyclerView()

        initObserver()

        binding.retryBtn.setOnClickListener {
            fetchData()
        }

        fetchData()
    }

    // fetch data from local or from api
    private fun fetchData(isFromLocal: Boolean = false) {
        binding.progressCircular.visibility = View.VISIBLE
        if (isFromLocal)
            viewModel.getTrendingAndroidReposListLocal()
        else
            viewModel.getTrendingAndroidReposList()
    }

    private fun initObserver() {
        viewModel.gitHubTrendingLiveData.observe(this) { resultData ->
            binding.progressCircular.visibility = View.GONE
            if (resultData is Result.Success) {
                if (resultData.data == null || resultData.data.size == 0) {
                    binding.statusTv.text = getString(R.string.no_record_found)
                    binding.statusView.visibility = View.VISIBLE
                    binding.retryBtn.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                } else {
                    adapter.setData(resultData.data)
                    binding.statusView.visibility = View.GONE
                    binding.retryBtn.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                }
            } else if (resultData is Result.Error) {
                resultData.exception.printStackTrace()
                binding.statusTv.text = getString(R.string.error_try_again)
                binding.statusView.visibility = View.VISIBLE
                binding.retryBtn.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            }
        }

        viewModel.gitHubTrendingLocalLiveData.observe(this) { resultData ->
            binding.progressCircular.visibility = View.GONE
            if (resultData is Result.Success) {
                if (resultData.data == null || resultData.data.size == 0) {
                    binding.statusTv.text = getString(R.string.no_record_found)
                    binding.statusView.visibility = View.VISIBLE
                    binding.retryBtn.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                } else {
                    adapter.setData(resultData.data)
                    binding.statusView.visibility = View.GONE
                    binding.retryBtn.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                }
            } else if (resultData is Result.Error) {
                resultData.exception.printStackTrace()
                binding.statusTv.text = getString(R.string.error_try_again)
                binding.statusView.visibility = View.VISIBLE
                binding.retryBtn.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            }
        }
    }


    private fun initRecyclerView() {
        adapter = RvListAdapter()
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
        binding.recyclerView.adapter = adapter
    }

}