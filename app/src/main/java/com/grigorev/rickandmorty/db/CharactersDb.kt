package com.grigorev.rickandmorty.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.grigorev.rickandmorty.dao.CharactersDao
import com.grigorev.rickandmorty.entity.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1, exportSchema = false)
abstract class CharactersDb: RoomDatabase(){

    abstract fun charactersDao(): CharactersDao

    companion object {
        @Volatile
        private var instance: CharactersDb? = null

        fun getInstance(context: Context): CharactersDb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context)
                    .also { instance = it }
            }
        }

        private fun buildDatabase(context: Context)
                = Room.databaseBuilder(context, CharactersDb::class.java, "Characters.db")
            .build()
    }
}