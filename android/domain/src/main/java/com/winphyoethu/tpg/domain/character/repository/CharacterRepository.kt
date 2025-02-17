package com.winphyoethu.tpg.domain.character.repository

import com.winphyoethu.tpg.core.common.result.TpgResult
import com.winphyoethu.tpg.core.model.character.Character
import com.winphyoethu.tpg.core.model.character.CharacterPaginationInfo
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.flow.Flow

/**
 * API for Character Repository
 */
interface CharacterRepository {

    /**
     * Get Character from API
     *
     * @param page is the pagination counter
     *
     * @return TpgResult [TpgResult] which type is CharacterPaginationInfo[CharacterPaginationInfo]
     */
    suspend fun getCharacter(page: Int): TpgResult<CharacterPaginationInfo>

    /**
     * Get Character from Local Database
     *
     * @return Flow of PersistentList of Character [Character]
     */
    fun getSavedCharacter(): Flow<PersistentList<Character>>

    /**
     * Get Character By Id from Local Database
     *
     * @return TpgResult [TpgResult] which type is Character[Character]
     */
    suspend fun getCharacterById(id: Int): TpgResult<Character>

    /**
     * Save Character to Database
     *
     * @param characterString is String of character pass from react native
     *
     * @return TpgResult [TpgResult] which type is Long(created row id)
     */
    suspend fun saveCharacter(characterString: String): TpgResult<Long>

    /**
     * Delete Character from Database
     *
     * @param characterString is String of character pass from react native
     *
     * @return TpgResult [TpgResult] which type is Integer(impacted number of rows)
     */
    suspend fun deleteCharacter(characterString: String): TpgResult<Int>

}