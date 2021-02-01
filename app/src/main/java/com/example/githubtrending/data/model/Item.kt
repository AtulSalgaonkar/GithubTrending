package com.example.githubtrending.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Item(

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("full_name")
    @Expose
    var fullName: String,

    @SerializedName("private")
    @Expose
    var _private: Boolean,

    @SerializedName("owner")
    @Expose
    var owner: Owner,

    @SerializedName("html_url")
    @Expose
    var htmlUrl: String,

    @SerializedName("description")
    @Expose
    var description: String,

    @SerializedName("fork")
    @Expose
    var fork: Boolean,

    @SerializedName("created_at")
    @Expose
    var createdAt: String,

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String,

    @SerializedName("pushed_at")
    @Expose
    var pushedAt: String,

    @SerializedName("git_url")
    @Expose
    var gitUrl: String,

    @SerializedName("ssh_url")
    @Expose
    var sshUrl: String,

    @SerializedName("clone_url")
    @Expose
    var cloneUrl: String,

    @SerializedName("svn_url")
    @Expose
    var svnUrl: String,

    @SerializedName("homepage")
    @Expose
    var homepage: String,

    @SerializedName("size")
    @Expose
    var size: Int,

    @SerializedName("stargazers_count")
    @Expose
    var stargazersCount: Int,

    @SerializedName("watchers_count")
    @Expose
    var watchersCount: Int,

    @SerializedName("language")
    @Expose
    var language: String,

    @SerializedName("has_issues")
    @Expose
    var hasIssues: Boolean,

    @SerializedName("has_projects")
    @Expose
    var hasProjects: Boolean,

    @SerializedName("has_downloads")
    @Expose
    var hasDownloads: Boolean,

    @SerializedName("has_wiki")
    @Expose
    var hasWiki: Boolean,

    @SerializedName("has_pages")
    @Expose
    var hasPages: Boolean,

    @SerializedName("forks_count")
    @Expose
    var forksCount: Int,

    @SerializedName("archived")
    @Expose
    var archived: Boolean,

    @SerializedName("disabled")
    @Expose
    var disabled: Boolean,

    @SerializedName("open_issues_count")
    @Expose
    var openIssuesCount: Int,

    @SerializedName("forks")
    @Expose
    var forks: Int,

    @SerializedName("open_issues")
    @Expose
    var openIssues: Int,

    @SerializedName("watchers")
    @Expose
    var watchers: Int,

    @SerializedName("default_branch")
    @Expose
    var defaultBranch: String,

    @SerializedName("score")
    @Expose
    var score: Int,
)
