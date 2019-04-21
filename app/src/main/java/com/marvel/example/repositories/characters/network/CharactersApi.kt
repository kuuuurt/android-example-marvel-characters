package com.marvel.example.repositories.characters.network

import com.marvel.example.models.character.Character
import com.marvel.example.repositories.base.network.response.MarvelApiData
import com.marvel.example.repositories.base.network.response.MarvelApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Copyright (c) 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 19/04/2019
 */
interface CharactersApi {
    @GET("/v1/public/characters")
    fun getCharactersAsync(@Query("offset") offset: Int): Deferred<MarvelApiResponse<Character>>


    @GET("/v1/public/characters/{characterId}")
    fun getCharacterAsync(@Path("characterId") characterId: Int): Deferred<MarvelApiResponse<Character>>
}