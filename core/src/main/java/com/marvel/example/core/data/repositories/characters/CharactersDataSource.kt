package com.marvel.example.core.data.repositories.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PositionalDataSource
import com.marvel.example.core.ui.ActionState
import com.marvel.example.core.data.models.character.Character
import com.marvel.example.core.utils.livedata.Event
import com.marvel.example.core.utils.livedata.toEvent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * Copyright (c) 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 20/04/2019
 */
class CharactersDataSource(
    private val charactersRepository: CharactersRepository
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
                val marvelApiCharactersResponse =
                    CharactersRepository.getCharacters()

                callback.onResult(
                    marvelApiCharactersResponse.results,
                    0,
                    marvelApiCharactersResponse.total
                )
                _charactersState.postValue(ActionState.Complete.toEvent())
            } catch (exception: Exception) {
                _charactersState.postValue(ActionState.Error(exception.localizedMessage).toEvent())
            }
        }
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Character>) {
        GlobalScope.launch {
            try {
                val marvelApiCharactersResponse =
                    CharactersRepository.getCharacters(
                        params.startPosition
                    )

                callback.onResult(marvelApiCharactersResponse.results)
                _charactersState.postValue(ActionState.Complete.toEvent())
            } catch (exception: Exception) {
                _charactersState.postValue(ActionState.Error(exception.localizedMessage).toEvent())
            }
        }
    }
}