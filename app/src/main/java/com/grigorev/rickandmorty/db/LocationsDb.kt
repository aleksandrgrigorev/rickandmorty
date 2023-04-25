package com.grigorev.rickandmorty.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.grigorev.rickandmorty.dao.LocationsDao
import com.grigorev.rickandmorty.entity.LocationEntity

@Database(entities = [LocationEntity::class], version = 1, exportSchema = false)
abstract class LocationsDb: RoomDatabase(){

    abstract fun locationsDao(): LocationsDao

    companion object {
        @Volatile
        private var instance: LocationsDb? = null

        fun getInstance(context: Context): LocationsDb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context)
                    .also { instance = it }
            }
        }

        private fun buildDatabase(context: Context)
                = Room.databaseBuilder(context, LocationsDb::class.java, "Locations.db")
            .build()
    }
}