package com.grigorev.rickandmorty.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.grigorev.rickandmorty.entity.EpisodeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EpisodesDao {
    @Query("SELECT * FROM EpisodeEntity ORDER BY id ASC")
    fun getAll(): Flow<List<EpisodeEntity>>

    @Query("SELECT * FROM EpisodeEntity WHERE id = :id")
    fun getEpisodeById(id: Int): Flow<EpisodeEntity>

    @Insert(onConflict = REPLACE)
    suspend fun insert(episode: EpisodeEntity)

    @Insert(onConflict = REPLACE)
    suspend fun insert(episodes: List<EpisodeEntity>)
}