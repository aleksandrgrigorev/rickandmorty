package com.grigorev.rickandmorty.repository

import com.grigorev.rickandmorty.api.Api
import com.grigorev.rickandmorty.dao.EpisodesDao
import com.grigorev.rickandmorty.dto.Episode
import com.grigorev.rickandmorty.entity.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class EpisodeDetailsRepositoryImpl(private val dao: EpisodesDao, val id: Int) : EpisodeDetailsRepository {

    override val flow = dao.getEpisodeById(id)
        .map { it.toDto() }
        .flowOn(Dispatchers.Default)

    override suspend fun getEpisodeById(id: Int) {
        val episode: Episode
        try {
            val response = Api.apiClient.getEpisode(id)
            if (!response.isSuccessful) {
                throw Exception("Response was not successful")
            }
            episode = response.body()!!
            dao.insert(episode.toEntity())
        } catch (e: java.lang.Exception) {
            throw e
        }
    }

}