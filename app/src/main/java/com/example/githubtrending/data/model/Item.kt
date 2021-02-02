package com.example.githubtrending.data.model

import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "item_tbl",
    foreignKeys = [ForeignKey(
        entity = Owner::class,
        parentColumns = arrayOf("owner_uid"),
        childColumns = arrayOf("owner_uuid"),
        onDelete = ForeignKey.CASCADE
    )], indices = [Index(value = ["owner_uuid"], unique = true)]
)
class Item(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "item_id") var itemId: Int,
    @ColumnInfo(name = "owner_uuid") var ownerUuid: Int?,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose var name: String?,
    @ColumnInfo(name = "full_name")
    @SerializedName("full_name")
    @Expose var fullName: String?,
    @ColumnInfo(name = "private")
    @SerializedName("private")
    @Expose var _private: Boolean?,
    @SerializedName("html_url")
    @Expose var htmlUrl: String?,
    @ColumnInfo(name = "description")
    @SerializedName("description")
    @Expose var description: String?,
    @ColumnInfo(name = "fork")
    @SerializedName("fork")
    @Expose var fork: Boolean?,
    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    @Expose var createdAt: String?,
    @ColumnInfo(name = "updated_at")
    @SerializedName("updated_at")
    @Expose var updatedAt: String?,
    @ColumnInfo(name = "pushed_at")
    @SerializedName("pushed_at")
    @Expose var pushedAt: String?,
    @ColumnInfo(name = "git_url")
    @SerializedName("git_url")
    @Expose var gitUrl: String?,
    @ColumnInfo(name = "size")
    @SerializedName("size")
    @Expose var size: Int?,
    @ColumnInfo(name = "stargazers_count")
    @SerializedName("stargazers_count")
    @Expose var stargazersCount: Int?,
    @ColumnInfo(name = "watchers_count")
    @SerializedName("watchers_count")
    @Expose var watchersCount: Int?,
    @ColumnInfo(name = "language")
    @SerializedName("language")
    @Expose var language: String?,
    @ColumnInfo(name = "has_issues")
    @SerializedName("has_issues")
    @Expose var hasIssues: Boolean?,
    @ColumnInfo(name = "forks_count")
    @SerializedName("forks_count")
    @Expose var forksCount: Int?,
    @ColumnInfo(name = "archived")
    @SerializedName("archived")
    @Expose var archived: Boolean?,
    @ColumnInfo(name = "open_issues_count")
    @SerializedName("open_issues_count")
    @Expose var openIssuesCount: Int?,
    @ColumnInfo(name = "forks")
    @SerializedName("forks")
    @Expose var forks: Int?,
    @ColumnInfo(name = "open_issues")
    @SerializedName("open_issues")
    @Expose var openIssues: Int?,
    @ColumnInfo(name = "watchers")
    @SerializedName("watchers")
    @Expose var watchers: Int?,
    @ColumnInfo(name = "score")
    @SerializedName("score")
    @Expose var score: Int?
) {


    @SerializedName("owner")
    @Expose
    @Embedded
    var owner: Owner? = null

}
