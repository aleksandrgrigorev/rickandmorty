package com.grigorev.rickandmorty.repository

import com.grigorev.rickandmorty.api.Api
import com.grigorev.rickandmorty.dao.EpisodesDao
import com.grigorev.rickandmorty.dto.Episode
import com.grigorev.rickandmorty.entity.toDto
import com.grigorev.rickandmorty.entity.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class EpisodesRepositoryImpl(private val dao: EpisodesDao) : EpisodesRepository {

    override val data = dao.getAll()
        .map { it.toDto() }
        .flowOn(Dispatchers.Default)

    override suspend fun getAll(page: Int) {
        val body: List<Episode>
        try {
            val response = Api.apiClient.getAllEpisodes(page)
            if (!response.isSuccessful) {
                throw Exception("Response was not successful")
            }
            body = response.body()!!.results
            dao.insert(body.toEntity())
        } catch (e: Exception) {
            throw e
        }
    }

}