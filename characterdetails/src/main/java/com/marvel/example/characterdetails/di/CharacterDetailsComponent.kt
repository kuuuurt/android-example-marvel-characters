package com.marvel.example.characterdetails.di

import com.marvel.example.characterdetails.presentation.CharacterDetailsActivity
import com.marvel.example.core.di.CoreComponent
import com.marvel.example.core.di.scopes.FeatureScope
import dagger.Component

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 06/02/2019
 */
@Component(dependencies = [CoreComponent::class], modules = [CharacterDetailsModule::class])
@FeatureScope
interface CharacterDetailsComponent {
    fun inject(characterDetailsActivity: CharacterDetailsActivity)
}