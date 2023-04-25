package com.grigorev.rickandmorty.ui.characters

import com.grigorev.rickandmorty.dto.Character

data class CharactersModel(
    val characters: List<Character> = emptyList(),
    val empty: Boolean = false,
)

data class CharactersModelState(
    val loading: Boolean = false,
    val error: Boolean = false,
    val refreshing: Boolean = false,
)