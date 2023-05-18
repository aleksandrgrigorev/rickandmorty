package com.grigorev.rickandmorty.repository

import com.grigorev.rickandmorty.api.Api
import com.grigorev.rickandmorty.dao.CharactersDao
import com.grigorev.rickandmorty.dto.Character
import com.grigorev.rickandmorty.entity.toDto
import com.grigorev.rickandmorty.entity.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class CharactersRepositoryImpl(private val dao: CharactersDao) : CharactersRepository {

    override val flow = dao.getAll()
        .map { it.toDto() }
        .flowOn(Dispatchers.Default)

    override suspend fun getAll(page: Int) {
        val body: List<Character>
        try {
            val response = Api.apiClient.getAllCharacters(page)
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