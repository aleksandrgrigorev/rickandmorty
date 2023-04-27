package com.grigorev.rickandmorty.dto

import androidx.room.TypeConverters
import com.grigorev.rickandmorty.db.Converters

data class CharactersApiResponse(
    val info: Info,
    val results: List<Character>,
)

@TypeConverters(Converters::class)
data class Character(
    val id: Int,
    val image: String,
    val name: String,
    val gender: String,
    val species: String,
    val status: String,
    val episode: List<String>,
    val origin: Origin,
    val location: LocationCharacter,
    val url: String
)

data class Origin(
    val name: String,
    val url: String
)

data class LocationCharacter(
    val name: String,
    val url: String
)