package com.grigorev.rickandmorty.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.grigorev.rickandmorty.db.Converters
import com.grigorev.rickandmorty.dto.Character
import com.grigorev.rickandmorty.dto.LocationCharacter
import com.grigorev.rickandmorty.dto.Origin

@Entity
@TypeConverters(Converters::class)
data class CharacterEntity(
    @PrimaryKey
    val id: Int,
    val gender: String,
    val image: String,
    val name: String,
    val species: String,
    val status: String,
    val episode: List<String>,
    @Embedded(prefix = "origin_")
    val origin: OriginEntity,
    @Embedded(prefix = "location_")
    val location: LocationCharacterEntity,
    val url: String,
) {
    fun toDto() = Character(
        id,
        image,
        name,
        gender,
        species,
        status,
        episode,
        origin.toDto(),
        location.toDto(),
        url
    )

    companion object {
        fun fromDto(dto: Character) =
            CharacterEntity(
                dto.id, dto.gender, dto.image, dto.name,
                dto.species, dto.status, dto.episode,
                OriginEntity.fromDto(dto.origin),
                LocationCharacterEntity.fromDto(dto.location),
                dto.url
            )
    }
}

@Entity
data class OriginEntity(
    val name: String,
    val url: String
) {
    fun toDto() = Origin(name, url)

    companion object {
        fun fromDto(dto: Origin): OriginEntity {
            return OriginEntity(dto.name, dto.url)
        }
    }
}

@Entity
data class LocationCharacterEntity(
    val name: String,
    val url: String
) {
    fun toDto() = LocationCharacter(name, url)

    companion object {
        fun fromDto(dto: LocationCharacter): LocationCharacterEntity {
            return LocationCharacterEntity(dto.name, dto.url)
        }
    }
}

fun List<CharacterEntity>.toDto() = map { it.toDto() }

fun List<Character>.toEntity() = map { CharacterEntity.fromDto(it) }

fun Character.toEntity() = CharacterEntity.fromDto(
    Character(id, image, name, gender, species, status, episode, origin, location, url)
)