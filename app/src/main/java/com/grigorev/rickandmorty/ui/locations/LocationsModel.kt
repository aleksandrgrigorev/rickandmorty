package com.grigorev.rickandmorty.ui.locations

import com.grigorev.rickandmorty.dto.Location

data class LocationsModel(
    val locations: List<Location> = emptyList(),
    val empty: Boolean = false,
)

data class LocationsModelState(
    val loading: Boolean = false,
    val error: Boolean = false,
    val refreshing: Boolean = false,
)