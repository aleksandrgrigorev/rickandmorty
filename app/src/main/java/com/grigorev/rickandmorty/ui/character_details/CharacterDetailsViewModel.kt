package com.grigorev.rickandmorty.ui.character_details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.grigorev.rickandmorty.db.AppDb
import com.grigorev.rickandmorty.dto.Character
import com.grigorev.rickandmorty.repository.CharacterDetailsRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(application: Application, id: Int) : AndroidViewModel(application) {

    private val repository = CharacterDetailsRepositoryImpl(AppDb.getInstance(application).charactersDao(), id)
    val flow : Flow<Character> = repository.flow
        .flowOn(Dispatchers.Default)

    fun loadCharacter(id: Int) = viewModelScope.launch {
        try {
            repository.getCharacterById(id)
        } catch (e: Exception) {
            throw e
        }
    }

}