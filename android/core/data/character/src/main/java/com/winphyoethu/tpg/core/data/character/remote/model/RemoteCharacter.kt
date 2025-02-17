package com.winphyoethu.tpg.core.data.character.remote.model

import com.squareup.moshi.Json

/**
 * Remote Character list
 */
data class RemoteCharacterList(
    @Json(name = "info")
    val paginationInfo: PaginationInfo,
    @Json(name = "results")
    val characterList: List<RemoteCharacter>
)

/**
 * Pagination Information
 */
data class PaginationInfo(
    @Json(name = "count")
    val count: Int,
    @Json(name = "pages")
    val page: Int,
    @Json(name = "next")
    val next: String?,
    @Json(name = "prev")
    val prev: String?
)

/**
 * Remote Character
 */
data class RemoteCharacter(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "status")
    val status: String,
    @Json(name = "species")
    val species: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "gender")
    val gender: String,
    @Json(name = "origin")
    val origin: Location,
    @Json(name = "location")
    val location: Location,
    @Json(name = "image")
    val image: String,
    @Json(name = "episodes")
    val episodes: List<String>?,
    @Json(name = "url")
    val url: String
)

/**
 * Remote Character
 */
data class Location(
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String
)

val mockLocation = Location(
    name = "earth",
    url = "url"
)

val mockRemoteCharacter = RemoteCharacter(
    id = 1,
    name = "Rick",
    status = "Alive",
    species = "Human",
    type = "type",
    gender = "Male",
    origin = mockLocation,
    location = mockLocation,
    image = "image",
    episodes = listOf(),
    url = "url"
)

val mockPaginationInfo = PaginationInfo(
    count = 1,
    page = 1,
    next = "",
    prev = ""
)

val mockRemoteCharacterList = RemoteCharacterList(
    paginationInfo = mockPaginationInfo,
    characterList = listOf(mockRemoteCharacter)
)