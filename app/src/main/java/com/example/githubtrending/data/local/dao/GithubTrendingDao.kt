package com.example.githubtrending.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.githubtrending.data.model.Item
import com.example.githubtrending.data.model.Owner

@Dao
interface GithubTrendingDao {

    @Query("SELECT * FROM item_tbl")
    fun getItem(): List<Item>

    @Insert
    fun insertItem(item: Item)

    @Insert
    fun insertItems(countryAll: List<Item>)

    @Delete
    fun deleteItem(item: Item)

    @Query("DELETE FROM item_tbl")
    fun nukeItemTable()


    @Query("SELECT * FROM owner_tbl")
    fun getOwner(): List<Owner>

    @Insert
    fun insertOwner(Owner: Owner) : Long

    @Insert
    fun insertOwner(countryAll: List<Owner>)

    @Delete
    fun deleteOwner(owner: Owner)

    @Query("DELETE FROM owner_tbl")
    fun nukeOwnerTable()

}