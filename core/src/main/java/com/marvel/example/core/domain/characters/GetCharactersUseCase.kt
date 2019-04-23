package com.marvel.example.core.domain.characters

import com.marvel.example.core.data.repositories.characters.CharactersRepository
import com.marvel.example.core.data.models.None
import com.marvel.example.core.data.repositories.characters.CharactersDataSource
import com.marvel.example.core.domain.base.BaseUseCase

/**
 * Copyright (c) 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 19/04/2019
 */
class GetCharactersUseCase(
    private val charactersRepository: CharactersRepository
) : BaseUseCase<None, CharactersDataSource>() {
    override suspend fun execute(params: None) = CharactersDataSource(charactersRepository)
}