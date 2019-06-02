package com.marvel.example.characterdetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marvel.example.characterdetails.domain.GetCharacterDetails

/**
 * Copyright (c) 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 19/04/2019
 */
class CharacterDetailsViewModelFactory(
    private val characterId: Int,
    private val getCharacterDetails: GetCharacterDetails
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterDetailsViewModel::class.java)) {
            return CharacterDetailsViewModel(characterId, getCharacterDetails) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")

    }
}