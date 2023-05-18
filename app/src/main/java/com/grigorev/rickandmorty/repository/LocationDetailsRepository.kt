package com.grigorev.rickandmorty.repository

import com.grigorev.rickandmorty.dto.Location
import kotlinx.coroutines.flow.Flow

interface LocationDetailsRepository {
    val flow: Flow<Location>

    suspend fun getLocationById(id: Int)
}