package com.marvel.example.characterdetails.domain

import com.marvel.example.core.domain.repositories.CharactersRepository
import javax.inject.Inject


/**
 * Copyright (c) 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 21/04/2019
 */
class GetCharacterDetails @Inject constructor(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(characterId: Int) = charactersRepository.getCharacter(characterId)
}

