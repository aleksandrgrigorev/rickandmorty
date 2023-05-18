package com.grigorev.rickandmorty.repository

import com.grigorev.rickandmorty.dto.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    val flow: Flow<List<Character>>

    suspend fun getAll(page: Int)
}