package com.grigorev.rickandmorty.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.grigorev.rickandmorty.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {
    @Query("SELECT * FROM CharacterEntity ORDER BY id ASC")
    fun getAll(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM CharacterEntity WHERE id = :id")
    fun getCharacterById(id: Int): Flow<CharacterEntity>

    @Insert(onConflict = REPLACE)
    suspend fun insert(character: CharacterEntity)

    @Insert(onConflict = REPLACE)
    suspend fun insert(characters: List<CharacterEntity>)
}