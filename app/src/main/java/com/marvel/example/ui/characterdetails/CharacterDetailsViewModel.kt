package com.marvel.example.ui.characterdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marvel.example.ui.base.ActionState
import com.marvel.example.ui.base.BaseViewModel
import com.marvel.example.utils.livedata.Event
import com.marvel.example.utils.livedata.toEvent
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * Copyright (c) 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 19/04/2019
 */
class CharacterDetailsViewModel(
    private val characterId: Int,
    private val getCharacterDetails: GetCharacterDetailsUseCase
) : BaseViewModel() {
    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    private val _thumbnailUrl = MutableLiveData<String>()
    val thumbnailUrl: LiveData<String> = _thumbnailUrl

    private val _getCharacterDetailsState = MutableLiveData<Event<ActionState>>()
    val getCharacterDetailsState: LiveData<Event<ActionState>> = _getCharacterDetailsState

    init {
        getCharacterDetails()
    }

    fun getCharacterDetails() {
        uiScope.launch {
            _getCharacterDetailsState.postValue(ActionState.Loading.toEvent())
            try {
                val character = getCharacterDetails.execute(characterId)

                _name.postValue(character.name)
                _description.postValue(character.description)
                _thumbnailUrl.postValue(character.thumbnail.getUrl())

                _getCharacterDetailsState.postValue(ActionState.Complete.toEvent())
            } catch (exception: Exception) {
                _getCharacterDetailsState.postValue(ActionState.Error(exception.localizedMessage).toEvent())
            }
        }
    }
}