package com.marvel.example.core.di

import com.marvel.example.core.data.repositories.characters.CharactersRemoteSource
import com.marvel.example.core.data.repositories.characters.CharactersRepositoryImpl
import com.marvel.example.core.domain.repositories.CharactersRepository
import com.marvel.example.core.framework.characters.CharactersRemoteSourceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 06/02/2019
 */
@Module
abstract class CoreModule {
    @Binds
    @Singleton
    abstract fun providesCharactersRepository(charactersRepositoryImpl: CharactersRepositoryImpl): CharactersRepository

    @Binds
    @Singleton
    abstract fun providesCharactersRemoteSource(charactersRemoteSourceImpl: CharactersRemoteSourceImpl): CharactersRemoteSource
}