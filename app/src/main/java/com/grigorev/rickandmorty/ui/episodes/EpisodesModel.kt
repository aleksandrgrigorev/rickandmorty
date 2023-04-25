package com.grigorev.rickandmorty.ui.episodes

import com.grigorev.rickandmorty.dto.Episode

data class EpisodesModel(
    val episodes: List<Episode> = emptyList(),
    val empty: Boolean = false,
)

data class EpisodesModelState(
    val loading: Boolean = false,
    val error: Boolean = false,
    val refreshing: Boolean = false,
)