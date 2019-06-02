package com.marvel.example.characters.di

import com.marvel.example.characters.presentation.CharactersActivity
import com.marvel.example.core.di.CoreComponent
import com.marvel.example.core.di.scopes.FeatureScope
import dagger.Component

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 06/02/2019
 */
@Component(dependencies = [CoreComponent::class])
@FeatureScope
interface CharactersComponent {
    fun inject(activity: CharactersActivity)
}