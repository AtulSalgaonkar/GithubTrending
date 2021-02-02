package com.example.githubtrending.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "owner_tbl")
data class Owner(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "owner_uid")
    var ownerUid: Int,

    @ColumnInfo(name = "git_id")
    @SerializedName("id")
    @Expose
    var id: Int,

    @ColumnInfo(name = "login")
    @SerializedName("login")
    @Expose
    var login: String,

    @ColumnInfo(name = "node_id")
    @SerializedName("node_id")
    @Expose
    var nodeId: String,

    @ColumnInfo(name = "avatar_url")
    @SerializedName("avatar_url")
    @Expose
    var avatarUrl: String,

    @ColumnInfo(name = "gravatar_id")
    @SerializedName("gravatar_id")
    @Expose
    var gravatarId: String,

    @ColumnInfo(name = "owner_url")
    @SerializedName("url")
    @Expose
    var url: String,

    @ColumnInfo(name = "owner_html_url")
    @SerializedName("html_url")
    @Expose
    var ownerHtmlUrl: String,

    @ColumnInfo(name = "followers_url")
    @SerializedName("followers_url")
    @Expose
    var followersUrl: String,

    @ColumnInfo(name = "following_url")
    @SerializedName("following_url")
    @Expose
    var followingUrl: String,

    @ColumnInfo(name = "gists_url")
    @SerializedName("gists_url")
    @Expose
    var gistsUrl: String,

    @ColumnInfo(name = "starred_url")
    @SerializedName("starred_url")
    @Expose
    var starredUrl: String,

    @ColumnInfo(name = "subscriptions_url")
    @SerializedName("subscriptions_url")
    @Expose
    var subscriptionsUrl: String,

    @ColumnInfo(name = "organizations_url")
    @SerializedName("organizations_url")
    @Expose
    var organizationsUrl: String,

    @ColumnInfo(name = "repos_url")
    @SerializedName("repos_url")
    @Expose
    var reposUrl: String,

    @ColumnInfo(name = "events_url")
    @SerializedName("events_url")
    @Expose
    var eventsUrl: String,

    @ColumnInfo(name = "received_events_url")
    @SerializedName("received_events_url")
    @Expose
    var receivedEventsUrl: String,

    @ColumnInfo(name = "type")
    @SerializedName("type")
    @Expose
    var type: String,

    @ColumnInfo(name = "site_admin")
    @SerializedName("site_admin")
    @Expose
    var siteAdmin : Boolean

)
