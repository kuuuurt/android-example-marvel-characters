package com.marvel.example.characterdetails.di

import com.marvel.example.characterdetails.domain.GetCharacterDetails
import com.marvel.example.characterdetails.presentation.CharacterDetailsViewModelFactory
import com.marvel.example.core.di.scopes.FeatureScope
import dagger.Module
import dagger.Provides

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 06/02/2019
 */
@Module
class CharacterDetailsModule(private val characterId: Int) {
    @Provides
    @FeatureScope
    fun providesCharacterDetailsViewModelFactory(getCharacterDetails: GetCharacterDetails) =
        CharacterDetailsViewModelFactory(characterId, getCharacterDetails)
}