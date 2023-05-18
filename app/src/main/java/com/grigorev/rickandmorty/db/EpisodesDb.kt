package com.grigorev.rickandmorty.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.grigorev.rickandmorty.dao.EpisodesDao
import com.grigorev.rickandmorty.entity.EpisodeEntity

@Database(entities = [EpisodeEntity::class], version = 1, exportSchema = false)
abstract class EpisodesDb: RoomDatabase(){

    abstract fun episodesDao(): EpisodesDao

    companion object {
        @Volatile
        private var instance: EpisodesDb? = null

        fun getInstance(context: Context): EpisodesDb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context)
                    .also { instance = it }
            }
        }

        private fun buildDatabase(context: Context)
                = Room.databaseBuilder(context, EpisodesDb::class.java, "Episodes.db")
            .build()
    }
}