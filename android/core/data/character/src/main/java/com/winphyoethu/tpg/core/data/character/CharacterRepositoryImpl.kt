package com.winphyoethu.tpg.core.data.character

import Dispatcher
import com.squareup.moshi.Moshi
import com.winphyoethu.tpg.core.common.result.TpgResult
import com.winphyoethu.tpg.core.data.character.local.CharacterLocalDatasource
import com.winphyoethu.tpg.core.data.character.remote.datasource.CharacterRemoteDatasource
import com.winphyoethu.tpg.core.data.character.remote.model.RemoteCharacter
import com.winphyoethu.tpg.core.model.character.Character
import com.winphyoethu.tpg.core.model.character.CharacterPaginationInfo
import com.winphyoethu.tpg.core.model.character.PaginationInfo
import com.winphyoethu.tpg.domain.character.errorcode.CharacterErrorCode
import com.winphyoethu.tpg.domain.character.errorcode.CharacterLocalErrorCode
import com.winphyoethu.tpg.domain.character.repository.CharacterRepository
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Implementation of CharacterRepository [CharacterRepository]
 *
 * @param remoteDatasource Remote Data Source of Character [CharacterRemoteDatasource]
 * @param localDataSource Remote Data Source of Character [CharacterLocalDatasource]
 * @param moshi to parse character string to RemoteCharacter [RemoteCharacter]
 * @param io Coroutine IO Dispatcher
 */
internal class CharacterRepositoryImpl @Inject constructor(
    val remoteDatasource: CharacterRemoteDatasource,
    val localDataSource: CharacterLocalDatasource,
    val moshi: Moshi,
    @Dispatcher(TpgDispatchers.IO) val io: CoroutineDispatcher
) : CharacterRepository {

    override suspend fun getCharacter(page: Int): TpgResult<CharacterPaginationInfo> {
        return withContext(io) {
            try {
                val response = remoteDatasource.getCharacter(page)

                if (response.isSuccessful && response.body() != null) {
                    TpgResult.Success(
                        CharacterPaginationInfo(
                            paginationInfo = PaginationInfo(
                                response.body()!!.paginationInfo.next,
                                response.body()!!.paginationInfo.prev
                            ),
                            characterList = response.body()!!.characterList.map {
                                Character(
                                    id = it.id,
                                    name = it.name,
                                    origin = it.origin.name,
                                    image = it.image,
                                    status = it.status,
                                    episode = it.episodes?.toPersistentList() ?: persistentListOf()
                                )
                            }
                        )
                    )
                } else {
                    when (response.code()) {
                        401 -> TpgResult.Error(CharacterErrorCode.AuthenticationError("Authentication Error"))
                        else -> TpgResult.Error(CharacterErrorCode.UnknownError("Unknown Error"))
                    }
                }
            } catch (e: Exception) {
                TpgResult.Error(CharacterErrorCode.UnexpectedError("Unexpected Error"))
            }
        }
    }

    override fun getSavedCharacter(): Flow<PersistentList<Character>> {
        return localDataSource.getCharacterList().flowOn(io)
            .map {
                it.map { character ->
                    Character(
                        id = character.id,
                        name = character.name,
                        origin = character.origin,
                        image = character.image,
                        status = character.status
                    )
                }.toPersistentList()
            }
    }

    override suspend fun getCharacterById(id: Int): TpgResult<Character> {

        return withContext(io) {
            try {
                val result = localDataSource.getCharacterById(id)
                if (result != null) {
                    TpgResult.Success(
                        Character(
                            result.id,
                            result.name,
                            result.origin,
                            result.image,
                            result.status
                        )
                    )
                } else {
                    TpgResult.Error(CharacterLocalErrorCode.GetCharacterByIdError("Get Character by Id Error"))
                }
            } catch (e: Exception) {
                TpgResult.Error(CharacterLocalErrorCode.GetCharacterByIdError("Get Character by Id Error"))
            }
        }
    }

    override suspend fun saveCharacter(characterString: String): TpgResult<Long> {
        return withContext(io) {
            try {
                val adapter = moshi.adapter(RemoteCharacter::class.java)
                val remoteCharacter = adapter.fromJson(characterString)

                if (remoteCharacter != null) {
                    val result = localDataSource.saveCharacter(
                        remoteCharacter.id,
                        remoteCharacter.name,
                        remoteCharacter.origin.name,
                        remoteCharacter.image,
                        remoteCharacter.status
                    )

                    TpgResult.Success(result)
                } else {
                    TpgResult.Error(CharacterLocalErrorCode.SaveCharacterError("Save Character Error"))
                }
            } catch (e: Exception) {
                TpgResult.Error(CharacterLocalErrorCode.SaveCharacterError("Save Character Error"))
            }
        }
    }

    override suspend fun deleteCharacter(characterString: String): TpgResult<Int> {
        return withContext(io) {
            try {
                val adapter = moshi.adapter(RemoteCharacter::class.java)
                val remoteCharacter = adapter.fromJson(characterString)

                if (remoteCharacter != null) {
                    val result = localDataSource.deleteCharacter(remoteCharacter.id)

                    TpgResult.Success(result)
                } else {
                    TpgResult.Error(CharacterLocalErrorCode.DeleteCharacterError("Delete Character Error"))
                }
            } catch (e: Exception) {
                TpgResult.Error(CharacterLocalErrorCode.DeleteCharacterError("Delete Character Error"))
            }
        }
    }

}