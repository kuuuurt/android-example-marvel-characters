package com.marvel.example.characters.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.marvel.example.core.models.None
import com.marvel.example.core.repositories.characters.CharactersDataSource
import com.marvel.example.core.ui.base.ActionState
import com.marvel.example.core.ui.base.BaseViewModel
import com.marvel.example.core.utils.livedata.Event
import com.marvel.example.core.models.character.Character
import kotlinx.coroutines.runBlocking

/**
 * Copyright (c) 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 18/04/2019
 */
class CharactersViewModel(
    private val getCharacters: GetCharactersUseCase
) : BaseViewModel() {


    private val charactersDataSourceFactory = object : DataSource.Factory<Int, Character>() {
        private val _charactersDataSource = MutableLiveData<CharactersDataSource>()
        val charactersDataSource: LiveData<CharactersDataSource> = _charactersDataSource

        override fun create(): DataSource<Int, Character> {
            val newCharactersDataSource = runBlocking {
                getCharacters.execute(None.INSTANCE)
            }
            _charactersDataSource.postValue(newCharactersDataSource)
            return newCharactersDataSource
        }
    }

    private val config = PagedList.Config.Builder()
        .setPageSize(20)
        .setEnablePlaceholders(true)
        .build()

    val characters: LiveData<PagedList<Character>> =
        LivePagedListBuilder<Int, Character>(charactersDataSourceFactory, config).build()
    val charactersState: LiveData<Event<ActionState>> =
        Transformations.switchMap(charactersDataSourceFactory.charactersDataSource) { it.charactersState }

    fun refresh() = charactersDataSourceFactory.charactersDataSource.value?.invalidate()
}