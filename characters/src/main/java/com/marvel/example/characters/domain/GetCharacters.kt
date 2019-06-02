package com.marvel.example.characters.domain

import com.marvel.example.core.domain.repositories.CharactersRepository
import javax.inject.Inject


/**
 * Copyright (c) 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 19/04/2019
 */
class GetCharacters @Inject constructor(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(offset: Int = 0) = charactersRepository.getCharacters(offset)
}