package com.marvel.example.characters.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.marvel.example.characters.domain.GetCharacters
import com.marvel.example.core.domain.entities.character.Character
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

/**
 * Copyright (c) 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 20/04/2019
 */
class CharactersDataSourceFactory(
    private val getCharacters: GetCharacters,
    private val viewModelScope: CoroutineScope
) : DataSource.Factory<Int, Character>() {
    private val _charactersDataSource = MutableLiveData<CharactersDataSource>()
    val charactersDataSource: LiveData<CharactersDataSource> = _charactersDataSource

    override fun create(): DataSource<Int, Character> {
        val newCharactersDataSource = CharactersDataSource(getCharacters, viewModelScope)
        _charactersDataSource.postValue(newCharactersDataSource)
        return newCharactersDataSource
    }
}