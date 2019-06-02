package com.marvel.example.characterdetails.domain

import com.marvel.example.core.domain.entities.character.Character
import com.marvel.example.core.data.repositories.characters.CharactersRepository
import com.marvel.example.core.domain.BaseUseCase

/**
 * Copyright (c) 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 21/04/2019
 */
class GetCharacterDetailsUseCase(
    private val charactersRepository: CharactersRepository
) : BaseUseCase<Int, Character>() {
    override suspend fun execute(params: Int) = charactersRepository.getCharacter(params)
}

