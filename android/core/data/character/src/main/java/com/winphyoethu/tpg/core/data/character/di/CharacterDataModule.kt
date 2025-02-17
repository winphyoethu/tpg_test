package com.winphyoethu.tpg.core.data.character.di

import com.winphyoethu.tpg.core.data.character.CharacterRepositoryImpl
import com.winphyoethu.tpg.core.data.character.local.CharacterLocalDatasource
import com.winphyoethu.tpg.core.data.character.local.CharacterLocalDatasourceImpl
import com.winphyoethu.tpg.core.data.character.remote.apiservice.CharacterApiService
import com.winphyoethu.tpg.core.data.character.remote.datasource.CharacterRemoteDatasource
import com.winphyoethu.tpg.core.data.character.remote.datasource.CharacterRemoteDatasourceImpl
import com.winphyoethu.tpg.domain.character.repository.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class CharacterApiModule {

    @Provides
    fun provideCharacterApiService(retrofit: Retrofit): CharacterApiService {
        return retrofit.create(CharacterApiService::class.java)
    }

}

@InstallIn(SingletonComponent::class)
@Module
abstract class CharacterDataModule {

    @Binds
    internal abstract fun bindCharacterRemoteDatasource(characterRemoteDatasourceImpl: CharacterRemoteDatasourceImpl): CharacterRemoteDatasource

    @Binds
    internal abstract fun bindCharacterLocalDatasource(characterLocalDatasourceImpl: CharacterLocalDatasourceImpl): CharacterLocalDatasource

    @Binds
    internal abstract fun bindCharacterRepository(characterRepositoryImpl: CharacterRepositoryImpl): CharacterRepository

}