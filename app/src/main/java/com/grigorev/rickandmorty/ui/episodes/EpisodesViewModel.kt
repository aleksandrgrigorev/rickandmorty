package com.grigorev.rickandmorty.ui.episodes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.grigorev.rickandmorty.INITIAL_PAGE
import com.grigorev.rickandmorty.db.AppDb
import com.grigorev.rickandmorty.dto.Episode
import com.grigorev.rickandmorty.repository.EpisodesRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class EpisodesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = EpisodesRepositoryImpl(AppDb.getInstance(application).episodesDao())
    val flow : Flow<List<Episode>> = repository.flow
        .flowOn(Dispatchers.Default)

    init {
        loadEpisodes(INITIAL_PAGE)
    }

    fun loadEpisodes(page: Int) = viewModelScope.launch {
        try {
            repository.getAll(page)
        } catch (e: Exception) {
            throw e
        }
    }
}