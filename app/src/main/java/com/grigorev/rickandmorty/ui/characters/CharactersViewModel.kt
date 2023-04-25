package com.grigorev.rickandmorty.ui.characters

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.grigorev.rickandmorty.db.CharactersDb
import com.grigorev.rickandmorty.repository.CharactersRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class CharactersViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CharactersRepositoryImpl(CharactersDb.getInstance(application).charactersDao())
    val data : LiveData<CharactersModel> = repository.data
        .map { CharactersModel(it, it.isEmpty()) }
        .asLiveData(Dispatchers.Default)

    init {
        loadCharacters(1)
    }

    fun loadCharacters(page: Int) = viewModelScope.launch {
        try {
            repository.getAll(page)
        } catch (e: Exception) {
            throw e
        }
    }

}