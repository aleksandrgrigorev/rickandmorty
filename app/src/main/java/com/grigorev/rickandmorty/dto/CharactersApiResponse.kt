package com.grigorev.rickandmorty.dto

data class CharactersApiResponse(
    val info: Info,
    val results: List<Character>,
)

data class Character(
    val gender: String,
    val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val status: String,
)