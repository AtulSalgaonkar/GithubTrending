package com.example.githubtrending.ui.repository

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.githubtrending.R
import com.example.githubtrending.data.model.Item
import com.example.githubtrending.data.model.Owner
import com.example.githubtrending.databinding.ActivityRepositoryBinding

class RepositoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRepositoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepositoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item = intent.getParcelableExtra<Item>("item_key")
        val owner = intent.getParcelableExtra<Owner>("owner_key")

        item?.apply {
            supportActionBar?.title = owner?.login
            binding.avatarIv.let {
                Glide.with(it)
                    .load(owner?.avatarUrl)
                    .placeholder(R.drawable.android_logo)
                    .into(it)
            }
            binding.userNameTv.text = fullName
            binding.repoNameTv.text = name
            binding.archivedIv.visibility = if (archived == true) View.VISIBLE else View.INVISIBLE
            binding.repoDescriptionTv.text = description
            binding.repoUrlTv.text = htmlUrl
            binding.repoDateUpdatedTv.text = updatedAt
            binding.forkCountTv.text = forksCount.toString()
            binding.watchersCountTv.text = watchersCount.toString()
            binding.openIssuesCountTv.text = openIssuesCount.toString()
            binding.languageTv.text = language ?: "---"
            binding.stargazersCountTv.text = stargazersCount.toString()
        }

    }
}