package com.marvel.example.core.framework.characters

import com.marvel.example.core.data.repositories.characters.CharactersRemoteSource
import com.marvel.example.core.framework.BaseRemoteSource
import com.marvel.example.core.framework.characters.retrofit.CharactersApi
import javax.inject.Inject

/**
 * Copyright (c) 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 18/04/2019
 */
class CharactersRemoteSourceImpl @Inject constructor() : BaseRemoteSource<CharactersApi>(
    CharactersApi::class.java
), CharactersRemoteSource {
    override suspend fun getCharacters(offset: Int) = api.getCharactersAsync(offset).await().data
    override suspend fun getCharacter(id: Int) =
        api.getCharacterAsync(id).await().data.results.first()
}