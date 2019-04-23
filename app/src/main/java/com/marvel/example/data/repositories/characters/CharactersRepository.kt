package com.marvel.example.data.repositories.characters

import com.marvel.example.data.repositories.base.BaseRepository
import com.marvel.example.data.repositories.characters.network.CharactersApi

/**
 * Copyright (c) 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 18/04/2019
 */
object CharactersRepository : BaseRepository<CharactersApi>(CharactersApi::class.java) {
    suspend fun getCharacters(offset: Int = 0) = api.getCharactersAsync(offset).await().data
    suspend fun getCharacter(id: Int) = api.getCharacterAsync(id).await().data.results.first()
}