package com.grigorev.rickandmorty.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.grigorev.rickandmorty.entity.LocationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationsDao {
    @Query("SELECT * FROM LocationEntity ORDER BY id ASC")
    fun getAll(): Flow<List<LocationEntity>>

    @Query("SELECT * FROM LocationEntity WHERE id = :id")
    fun getLocationById(id: Int): Flow<LocationEntity>

    @Insert(onConflict = REPLACE)
    suspend fun insert(location: LocationEntity)

    @Insert(onConflict = REPLACE)
    suspend fun insert(locations: List<LocationEntity>)
}