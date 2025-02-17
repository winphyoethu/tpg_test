package com.winphyoethu.tpg.core.data.character.local

import com.winphyoethu.tpg.core.database.db.CharacterDao
import com.winphyoethu.tpg.core.database.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow
import java.util.Calendar
import javax.inject.Inject

/**
 * API for CharacterLocalDatasource
 *
 * @see CharacterLocalDatasourceImpl for actual implementation
 *
 */
interface CharacterLocalDatasource {

    /**
     * Get character by id
     *
     * @param id id of character
     * @return CharacterEntity[CharacterEntity] which is nullable
     */
    suspend fun getCharacterById(id: Int): CharacterEntity?

    /**
     * Save character
     *
     * @param id id of character
     * @param name name of character
     * @param origin id of character
     * @param image name of character
     * @param status id of character
     *
     * @return Long which is id of created row
     */
    suspend fun saveCharacter(
        id: Int,
        name: String,
        origin: String,
        image: String,
        status: String,
    ): Long

    /**
     * Get Saved Character List
     *
     * @return Flow of List of CharacterEntity[CharacterEntity]
     */
    fun getCharacterList(): Flow<List<CharacterEntity>>

    /**
     * Delete character
     * 
     * @param id id of character
     *
     * @return Int which is the number of rows impacted
     */
    suspend fun deleteCharacter(id: Int): Int

}

/**
 * Implementation of CharacterLocalDatasource [CharacterLocalDatasource]
 *
 * @param characterDao Dao for Character [CharacterDao]
 */
class CharacterLocalDatasourceImpl @Inject constructor(val characterDao: CharacterDao) :
    CharacterLocalDatasource {

    override suspend fun getCharacterById(id: Int): CharacterEntity? {
        return characterDao.getCharacterById(id)
    }

    override suspend fun saveCharacter(
        id: Int,
        name: String,
        origin: String,
        image: String,
        status: String,
    ): Long {
        return characterDao.saveCharacter(
            CharacterEntity(
                id = id,
                name = name,
                origin = origin,
                image = image,
                status = status,
                createdAt = Calendar.getInstance().timeInMillis
            )
        )
    }

    override fun getCharacterList(): Flow<List<CharacterEntity>> {
        return characterDao.getCharacterList()
    }

    override suspend fun deleteCharacter(id: Int): Int {
        return characterDao.deleteCharacter(id)
    }

}