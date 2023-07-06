package com.grigorev.rickandmorty.ui.location_details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.grigorev.rickandmorty.db.AppDb
import com.grigorev.rickandmorty.dto.Location
import com.grigorev.rickandmorty.repository.LocationDetailsRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class LocationDetailsViewModel(application: Application, val id: Int) : AndroidViewModel(application) {
    private val repository =
        LocationDetailsRepositoryImpl(AppDb.getInstance(application).locationsDao(), id)
    val flow: Flow<Location> = repository.flow
        .flowOn(Dispatchers.Default)

    fun loadLocationById(id: Int) = viewModelScope.launch {
        try {
            repository.getLocationById(id)
        } catch (e: Exception) {
            throw e
        }
    }
}