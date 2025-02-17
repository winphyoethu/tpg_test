package com.winphyoethu.tpg.core.database.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.winphyoethu.tpg.core.database.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

/**
 * Dao for Character
 */
@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacter(characterEntity: CharacterEntity): Long

    @Query("SELECT * FROM character WHERE id = :id")
    suspend fun getCharacterById(id: Int): CharacterEntity?

    @Query("SELECT * FROM character ORDER BY created_at")
    fun getCharacterList(): Flow<List<CharacterEntity>>

    @Query("DELETE from character WHERE id = :id")
    suspend fun deleteCharacter(id: Int): Int

}