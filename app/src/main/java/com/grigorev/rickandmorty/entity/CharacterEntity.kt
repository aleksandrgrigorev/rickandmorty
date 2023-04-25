package com.grigorev.rickandmorty.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.grigorev.rickandmorty.dto.Character

@Entity
data class CharacterEntity(
    @PrimaryKey
    val id: Int,
    val gender: String,
    val image: String,
    val name: String,
    val species: String,
    val status: String,
) {
    fun toDto() = Character(gender, id, image, name, species, status)

    companion object {
        fun fromDto(dto: Character) =
            CharacterEntity(dto.id, dto.gender, dto.image, dto.name, dto.species, dto.status)
    }
}

fun List<CharacterEntity>.toDto() = map { it.toDto() }

fun List<Character>.toEntity() = map { CharacterEntity.fromDto(it) }