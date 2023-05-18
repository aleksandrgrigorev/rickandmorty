package com.grigorev.rickandmorty.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.grigorev.rickandmorty.dto.Location

@Entity
data class LocationEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
) {
    fun toDto() = Location(id, name, type, dimension)

    companion object {
        fun fromDto(dto: Location) =
            LocationEntity(dto.id, dto.name, dto.type, dto.dimension)
    }
}

fun List<LocationEntity>.toDto() = map { it.toDto() }

fun List<Location>.toEntity() = map { LocationEntity.fromDto(it) }

fun Location.toEntity() = LocationEntity.fromDto(Location(id, name, type, dimension))