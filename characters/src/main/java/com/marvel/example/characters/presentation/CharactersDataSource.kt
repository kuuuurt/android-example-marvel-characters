package com.marvel.example.characters.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PositionalDataSource
import com.marvel.example.characters.domain.GetCharacters
import com.marvel.example.core.domain.entities.character.Character
import com.marvel.example.core.presentation.ActionState
import com.marvel.example.core.presentation.helpers.livedata.Event
import com.marvel.example.core.presentation.helpers.livedata.toEvent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Copyright (c) 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 20/04/2019
 */
class CharactersDataSource @Inject constructor(
    private val getCharacters: GetCharacters
) : PositionalDataSource<Character>() {
    private val _charactersState = MutableLiveData<Event<ActionState>>()
    val charactersState: LiveData<Event<ActionState>> = _charactersState

    override fun invalidate() {
        _charactersState.postValue(ActionState.Loading.toEvent())
        super.invalidate()
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Character>) {
        GlobalScope.launch {
            _charactersState.postValue(ActionState.Loading.toEvent())
            try {
                val getCharactersResult = getCharacters()

                callback.onResult(getCharactersResult.data, 0, getCharactersResult.totalData)
                _charactersState.postValue(ActionState.Complete.toEvent())
            } catch (exception: Exception) {
                _charactersState.postValue(ActionState.Error(exception.localizedMessage).toEvent())
            }
        }
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Character>) {
        GlobalScope.launch {
            try {
                val marvelApiCharactersResponse = getCharacters(params.startPosition)

                callback.onResult(marvelApiCharactersResponse.data)
                _charactersState.postValue(ActionState.Complete.toEvent())
            } catch (exception: Exception) {
                _charactersState.postValue(ActionState.Error(exception.localizedMessage).toEvent())
            }
        }
    }
}