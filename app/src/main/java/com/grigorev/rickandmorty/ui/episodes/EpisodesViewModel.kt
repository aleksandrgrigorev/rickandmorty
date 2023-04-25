package com.grigorev.rickandmorty.ui.episodes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.grigorev.rickandmorty.db.EpisodesDb
import com.grigorev.rickandmorty.repository.EpisodesRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class EpisodesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = EpisodesRepositoryImpl(EpisodesDb.getInstance(application).episodesDao())
    val data : LiveData<EpisodesModel> = repository.data
        .map { EpisodesModel(it, it.isEmpty()) }
        .asLiveData(Dispatchers.Default)

    init {
        loadEpisodes(1)
    }

    fun loadEpisodes(page: Int) = viewModelScope.launch {
        try {
            repository.getAll(page)
        } catch (e: Exception) {
            throw e
        }
    }
}