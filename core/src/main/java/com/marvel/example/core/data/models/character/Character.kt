package com.marvel.example.core.data.models.character

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Character(
    @Json(name = "id")
    val id: Int,

    @Json(name = "thumbnail")
    val thumbnail: Thumbnail,

    @Json(name = "name")
    val name: String,

    @Json(name = "description")
    val description: String,

    @Json(name = "modified")
    val modified: String
)