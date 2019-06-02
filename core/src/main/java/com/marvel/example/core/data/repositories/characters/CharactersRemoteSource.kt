package com.marvel.example.core.data.repositories.characters

import com.marvel.example.core.domain.entities.character.Character
import com.marvel.example.core.framework.models.response.MarvelApiData

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 06/02/2019
 */
interface CharactersRemoteSource {
    suspend fun getCharacters(offset: Int = 0): MarvelApiData<Character>
    suspend fun getCharacter(id: Int): Character
}