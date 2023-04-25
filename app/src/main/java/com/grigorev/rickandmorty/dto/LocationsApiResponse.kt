package com.grigorev.rickandmorty.dto

data class LocationsApiResponse(
    val info: Info,
    val results: List<Location>
)

data class Location(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
)