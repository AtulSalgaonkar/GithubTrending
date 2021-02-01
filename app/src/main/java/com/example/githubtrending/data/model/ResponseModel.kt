package com.example.githubtrending.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @SerializedName("total_count")
    @Expose
    var totalCount: Int = 0,

    @SerializedName("incomplete_results")
    @Expose
    var incompleteResults: Boolean = false,

    @SerializedName("items")
    @Expose
    var items: ArrayList<Item>? = null
)