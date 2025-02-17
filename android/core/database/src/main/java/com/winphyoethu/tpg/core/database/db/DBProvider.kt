package com.winphyoethu.tpg.core.database.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.Calendar

internal object DBProvider {

    fun create(context: Context): CharacterDatabase {
        return Room.databaseBuilder(context, CharacterDatabase::class.java, "character_database")
            .build()
    }

}