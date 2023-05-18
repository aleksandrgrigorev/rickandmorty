package com.grigorev.rickandmorty.ui.locations

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.grigorev.rickandmorty.INITIAL_PAGE
import com.grigorev.rickandmorty.db.LocationsDb
import com.grigorev.rickandmorty.dto.Location
import com.grigorev.rickandmorty.repository.LocationsRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class LocationsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = LocationsRepositoryImpl(LocationsDb.getInstance(application).locationsDao())
    val flow : Flow<List<Location>> = repository.flow
        .flowOn(Dispatchers.Default)

    init {
        loadLocations(INITIAL_PAGE)
    }

    fun loadLocations(page: Int) = viewModelScope.launch {
        try {
            repository.getAll(page)
        } catch (e: Exception) {
            throw e
        }
    }
}