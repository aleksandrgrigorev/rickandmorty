package com.grigorev.rickandmorty.repository

import com.grigorev.rickandmorty.dto.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodeDetailsRepository {
    val flow: Flow<Episode>

    suspend fun getEpisodeById(id: Int)
}