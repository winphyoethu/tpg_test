package com.winphyoethu.tpg.core.database.di

import android.content.Context
import com.winphyoethu.tpg.core.database.db.CharacterDao
import com.winphyoethu.tpg.core.database.db.CharacterDatabase
import com.winphyoethu.tpg.core.database.db.DBProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    internal fun providesCharacterDatabase(@ApplicationContext context: Context): CharacterDatabase {
        return DBProvider.create(context)
    }

    @Provides
    internal fun providesCharacterDao(database: CharacterDatabase): CharacterDao {
        return database.createCharacterDao()
    }

}