package com.grigorev.rickandmorty.repository

import com.grigorev.rickandmorty.dto.Character
import kotlinx.coroutines.flow.Flow

interface CharacterDetailsRepository {
    val flow: Flow<Character>

    suspend fun getCharacterById(id: Int)
}