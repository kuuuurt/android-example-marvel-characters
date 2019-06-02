package com.marvel.example.characterdetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marvel.example.characterdetails.domain.GetCharacterDetails
import com.marvel.example.core.data.repositories.characters.CharactersRepositoryImpl
import com.marvel.example.core.framework.characters.CharactersRemoteSourceImpl

/**
 * Copyright (c) 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 19/04/2019
 */
class CharacterDetailsViewModelFactory(private val characterId: Int) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CharacterDetailsViewModel(
            characterId,
            GetCharacterDetails(CharactersRepositoryImpl(CharactersRemoteSourceImpl()))
        ) as T
    }
}