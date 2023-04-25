package com.grigorev.rickandmorty.dto

data class EpisodesApiResponse(
    val info: Info,
    val results: List<Episode>
)

data class Episode(
    val id: Int,
    val name: String,
    val episode: String,
    val air_date: String,
)