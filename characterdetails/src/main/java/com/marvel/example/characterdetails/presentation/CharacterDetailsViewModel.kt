package com.marvel.example.characterdetails.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marvel.example.characterdetails.domain.GetCharacterDetails
import com.marvel.example.core.presentation.UiState
import com.marvel.example.core.presentation.helpers.livedata.Event
import com.marvel.example.core.presentation.helpers.livedata.toEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

/**
 * Copyright (c) 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 19/04/2019
 */
class CharacterDetailsViewModel(
    private val characterId: Int,
    private val getCharacterDetails: GetCharacterDetails
) : ViewModel() {
    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    private val _thumbnailUrl = MutableLiveData<String>()
    val thumbnailUrl: LiveData<String> = _thumbnailUrl

    private val _getCharacterDetailsState = MutableLiveData<Event<UiState>>()
    val getCharacterDetailsUiState: LiveData<Event<UiState>> = _getCharacterDetailsState

    init {
        getCharacterDetails()
    }

    fun getCharacterDetails() {
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            _getCharacterDetailsState.postValue(UiState.Error(exception.localizedMessage).toEvent())
        }) {
            _getCharacterDetailsState.postValue(UiState.Loading.toEvent())
            val character = getCharacterDetails(characterId)

            _name.postValue(character.name)
            _description.postValue(character.description)
            _thumbnailUrl.postValue(character.thumbnail.getUrl())

            _getCharacterDetailsState.postValue(UiState.Complete.toEvent())
        }
    }
}