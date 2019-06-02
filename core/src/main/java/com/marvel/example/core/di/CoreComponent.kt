package com.marvel.example.core.di

import com.marvel.example.core.domain.repositories.CharactersRepository
import dagger.Component
import javax.inject.Singleton

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 06/02/2019
 */
@Component(modules = [CoreModule::class])
@Singleton
interface CoreComponent {
    fun providesCharacterRepository(): CharactersRepository
}