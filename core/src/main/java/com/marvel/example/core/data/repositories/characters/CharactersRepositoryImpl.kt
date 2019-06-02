package com.marvel.example.core.data.repositories.characters

import com.marvel.example.core.domain.entities.PaginatedData
import com.marvel.example.core.domain.entities.character.Character
import com.marvel.example.core.domain.repositories.CharactersRepository

/**
 * Copyright (c) 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 18/04/2019
 */
class CharactersRepositoryImpl(
    private val charactersRemoteSource: CharactersRemoteSource
) : CharactersRepository {
    override suspend fun getCharacters(offset: Int): PaginatedData<Character> {
        val getCharactersResult = charactersRemoteSource.getCharacters(offset)
        return PaginatedData(getCharactersResult.results, getCharactersResult.total)
    }

    override suspend fun getCharacter(id: Int) = charactersRemoteSource.getCharacter(id)
}