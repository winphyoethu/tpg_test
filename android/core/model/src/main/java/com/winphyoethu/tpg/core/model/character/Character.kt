package com.winphyoethu.tpg.core.model.character

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

/**
 * Character Pagination Info
 */
data class CharacterPaginationInfo(
    val paginationInfo: PaginationInfo,
    val characterList: List<Character>
)

/**
 * Character to be used in View
 */
@Stable
data class Character(
    val id: Int,
    val name: String,
    val origin: String,
    val image: String,
    val status: String,
    val episode: PersistentList<String> = persistentListOf()
)

/**
 * Mocked Character to be used in compose preview and test
 */
val mockCharacter = Character(
    id = 1,
    name = "Rick Sanchez",
    origin = "Earth (C-137)",
    image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
    status = "Alive",
    episode = persistentListOf(
        "https://rickandmortyapi.com/api/episode/1",
        "https://rickandmortyapi.com/api/episode/2",
        "https://rickandmortyapi.com/api/episode/3"
    )
)