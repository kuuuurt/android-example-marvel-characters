package com.marvel.example.characterdetails.domain

import com.marvel.example.core.domain.repositories.CharactersRepository


/**
 * Copyright (c) 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 21/04/2019
 */
class GetCharacterDetails(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(charactedId: Int) = charactersRepository.getCharacter(charactedId)
}

