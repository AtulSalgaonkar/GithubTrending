package com.example.githubtrending.data.local.dao

import androidx.room.*
import com.example.githubtrending.data.model.Item
import com.example.githubtrending.data.model.Owner

@Dao
interface GithubTrendingDao {

    @Query("SELECT * FROM item_tbl")
    fun getItem(): List<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: Item)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItems(countryAll: List<Item>)

    @Delete
    fun deleteItem(item: Item)

    @Query("DELETE FROM item_tbl")
    fun nukeItemTable()


    @Query("SELECT * FROM owner_tbl")
    fun getOwner(): List<Owner>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOwner(Owner: Owner) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOwner(countryAll: List<Owner>)

    @Delete
    fun deleteOwner(owner: Owner)

    @Query("DELETE FROM owner_tbl")
    fun nukeOwnerTable()

}