package com.marvel.example.core.domain.repositories

import com.marvel.example.core.domain.entities.PaginatedData
import com.marvel.example.core.domain.entities.character.Character

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 06/02/2019
 */
interface CharactersRepository {
    suspend fun getCharacters(offset: Int = 0): PaginatedData<Character>
    suspend fun getCharacter(id: Int): Character
}