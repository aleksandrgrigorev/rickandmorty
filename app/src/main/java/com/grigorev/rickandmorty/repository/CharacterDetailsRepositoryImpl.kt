package com.grigorev.rickandmorty.repository

import com.grigorev.rickandmorty.api.Api
import com.grigorev.rickandmorty.dao.CharactersDao
import com.grigorev.rickandmorty.dto.Character
import com.grigorev.rickandmorty.entity.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class CharacterDetailsRepositoryImpl(private val dao: CharactersDao, val id: Int) : CharacterDetailsRepository {

    override val flow = dao.getCharacterById(id)
        .map { it.toDto() }
        .flowOn(Dispatchers.Default)

    override suspend fun getCharacterById(id: Int) {
        val character: Character
        try {
            val response = Api.apiClient.getCharacter(id)
            if (!response.isSuccessful) {
                throw Exception("Response was not successful")
            }
            character = response.body()!!
            dao.insert(character.toEntity())
        } catch (e: java.lang.Exception) {
            throw e
        }
    }

}