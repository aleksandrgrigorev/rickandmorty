package com.grigorev.rickandmorty.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.grigorev.rickandmorty.dto.Episode

@Entity
data class EpisodeEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val episode: String,
    val air_date: String,
) {
    fun toDto() = Episode(id, name, episode, air_date)

    companion object {
        fun fromDto(dto: Episode) =
            EpisodeEntity(dto.id, dto.name, dto.episode, dto.air_date)
    }
}

fun List<EpisodeEntity>.toDto() = map { it.toDto() }

fun List<Episode>.toEntity() = map { EpisodeEntity.fromDto(it) }