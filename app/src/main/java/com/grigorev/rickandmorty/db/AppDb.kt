package com.grigorev.rickandmorty.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.grigorev.rickandmorty.dao.CharactersDao
import com.grigorev.rickandmorty.dao.EpisodesDao
import com.grigorev.rickandmorty.dao.LocationsDao
import com.grigorev.rickandmorty.entity.CharacterEntity
import com.grigorev.rickandmorty.entity.EpisodeEntity
import com.grigorev.rickandmorty.entity.LocationEntity

@Database(entities = [CharacterEntity::class, EpisodeEntity::class, LocationEntity::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDb: RoomDatabase(){

    abstract fun charactersDao(): CharactersDao

    abstract fun episodesDao(): EpisodesDao

    abstract fun locationsDao(): LocationsDao

    companion object {
        @Volatile
        private var instance: AppDb? = null

        fun getInstance(context: Context): AppDb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context)
                    .also { instance = it }
            }
        }

        private fun buildDatabase(context: Context)
                = Room.databaseBuilder(context, AppDb::class.java, "Characters.db")
            .build()
    }
}