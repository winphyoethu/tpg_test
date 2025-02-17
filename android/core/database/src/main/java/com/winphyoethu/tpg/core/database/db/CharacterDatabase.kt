package com.winphyoethu.tpg.core.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.winphyoethu.tpg.core.database.entity.CharacterEntity

/**
 * Character Database
 * version - 1
 */
@Database(
    version = 1, exportSchema = true,
    entities = [CharacterEntity::class]
)
internal abstract class CharacterDatabase : RoomDatabase() {

    abstract fun createCharacterDao(): CharacterDao

}