package com.example.githubtrending.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseModel(

    @SerializedName("items")
    @Expose
    var items: ArrayList<Item>? = null

)