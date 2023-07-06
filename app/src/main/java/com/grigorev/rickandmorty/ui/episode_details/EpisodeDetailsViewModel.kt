package com.grigorev.rickandmorty.ui.episode_details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.grigorev.rickandmorty.db.AppDb
import com.grigorev.rickandmorty.dto.Episode
import com.grigorev.rickandmorty.repository.EpisodeDetailsRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class EpisodeDetailsViewModel(application: Application, val id: Int) : AndroidViewModel(application) {
    private val repository =
        EpisodeDetailsRepositoryImpl(AppDb.getInstance(application).episodesDao(), id)
    val flow: Flow<Episode> = repository.flow
        .flowOn(Dispatchers.Default)

    fun loadEpisodeById(id: Int) = viewModelScope.launch {
        try {
            repository.getEpisodeById(id)
        } catch (e: Exception) {
            throw e
        }
    }
}