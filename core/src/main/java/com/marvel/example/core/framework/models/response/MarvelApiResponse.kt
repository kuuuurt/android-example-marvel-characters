package com.marvel.example.core.framework.models.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarvelApiResponse<T>(
    @Json(name = "code")
    val code: Int,

    @Json(name = "data")
    val data: MarvelApiData<T>,

    @Json(name = "status")
    val status: String
)