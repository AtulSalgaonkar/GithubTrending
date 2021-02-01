package com.example.githubtrending.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.githubtrending.R
import com.example.githubtrending.databinding.HomeActivityBinding
import com.example.githubtrending.data.model.Result
import com.example.githubtrending.ui.adapter.RvListAdapter

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: HomeActivityBinding
    private lateinit var viewModel: GitHubTrendingDetailsViewModel
    private lateinit var adapter: RvListAdapter
    private val TAG = "HomeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(GitHubTrendingDetailsViewModel::class.java)

        initRecyclerView()

        initObserve()

        binding.retryBtn.setOnClickListener {
            fetchData()
        }

        fetchData()
    }

    private fun fetchData() {
        binding.progressCircular.visibility = View.VISIBLE
        viewModel.getTrendingAndroidReposList()
    }

    private fun initObserve() {
        viewModel.gitHubTrendingLiveData.observe(this) { resultData ->
            binding.progressCircular.visibility = View.GONE
            if (resultData is Result.Success) {
                if (resultData.data == null || resultData.data.size == 0) {
                    Log.d(TAG, "initObserve: " + getString(R.string.no_record_found))
                    binding.statusTv.text = getString(R.string.no_record_found)
                    binding.statusView.visibility = View.VISIBLE
                    binding.retryBtn.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                } else {
                    Log.d(TAG, "initObserve resultData.data:\n" + resultData.data)
                    adapter.setData(resultData.data)
                    binding.statusView.visibility = View.GONE
                    binding.retryBtn.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                }
            } else if (resultData is Result.Error) {
                resultData.exception.printStackTrace()
                Log.d(TAG, "initObserve: " + resultData.exception.message)
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