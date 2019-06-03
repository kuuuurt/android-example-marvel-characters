package com.marvel.example.characters.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.marvel.example.characters.domain.GetCharacters
import com.marvel.example.core.presentation.UiState
import com.marvel.example.core.presentation.BaseViewModel
import com.marvel.example.core.domain.entities.character.Character
import com.marvel.example.core.presentation.helpers.livedata.Event
import javax.inject.Inject

/**
 * Copyright (c) 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 18/04/2019
 */
class CharactersViewModel @Inject constructor(
    getCharacters: GetCharacters
) : BaseViewModel() {
    private val charactersDataSourceFactory = CharactersDataSourceFactory(getCharacters)

    private val config = PagedList.Config.Builder()
        .setPageSize(20)
        .setEnablePlaceholders(true)
        .build()

    val characters: LiveData<PagedList<Character>> =
        LivePagedListBuilder<Int, Character>(charactersDataSourceFactory, config).build()
    val charactersUiState: LiveData<Event<UiState>> =
        Transformations.switchMap(charactersDataSourceFactory.charactersDataSource) { it.charactersUiState }

    fun refresh() = charactersDataSourceFactory.charactersDataSource.value?.invalidate()
}