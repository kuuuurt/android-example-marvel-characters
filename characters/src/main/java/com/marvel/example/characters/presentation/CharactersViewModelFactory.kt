package com.marvel.example.characters.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marvel.example.characters.domain.GetCharacters
import com.marvel.example.core.data.repositories.characters.CharactersRepositoryImpl
import com.marvel.example.core.framework.characters.CharactersRemoteSourceImpl

/**
 * Copyright (c) 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 19/04/2019
 */
class CharactersViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CharactersViewModel(
            GetCharacters(
                CharactersRepositoryImpl(
                    CharactersRemoteSourceImpl()
                )
            )
        ) as T
    }
}