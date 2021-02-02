package com.example.githubtrending.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubtrending.data.local.dao.GithubTrendingDao
import com.example.githubtrending.data.model.Item
import com.example.githubtrending.data.model.Owner

@Database(entities = [
    Item::class,
    Owner::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun githubTrendingDao(): GithubTrendingDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        // Get instance of local db
        fun getDatabase(context: Context): AppDatabase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "GitHubTrendingReposDb"
            ).build()
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}