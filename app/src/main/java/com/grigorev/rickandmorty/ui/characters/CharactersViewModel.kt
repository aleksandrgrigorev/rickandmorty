package com.grigorev.rickandmorty.ui.characters

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.grigorev.rickandmorty.INITIAL_PAGE
import com.grigorev.rickandmorty.db.CharactersDb
import com.grigorev.rickandmorty.dto.Character
import com.grigorev.rickandmorty.repository.CharactersRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class CharactersViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CharactersRepositoryImpl(CharactersDb.getInstance(application).charactersDao())
    val flow : Flow<List<Character>> = repository.flow
        .flowOn(Dispatchers.Default)

    init {
        loadCharacters(INITIAL_PAGE)
    }

    fun loadCharacters(page: Int) = viewModelScope.launch {
        try {
            repository.getAll(page)
        } catch (e: Exception) {
            throw e
        }
    }

}