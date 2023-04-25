package com.grigorev.rickandmorty.repository

import com.grigorev.rickandmorty.dto.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository {
    val data: Flow<List<Episode>>

    suspend fun getAll(page: Int)
}