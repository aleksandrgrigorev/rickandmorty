package com.grigorev.rickandmorty.repository

import com.grigorev.rickandmorty.dto.Location
import kotlinx.coroutines.flow.Flow

interface LocationsRepository {
    val flow: Flow<List<Location>>

    suspend fun getAll(page: Int)
}