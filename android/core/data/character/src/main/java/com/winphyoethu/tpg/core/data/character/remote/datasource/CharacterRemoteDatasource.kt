package com.winphyoethu.tpg.core.data.character.remote.datasource

import com.winphyoethu.tpg.core.data.character.remote.apiservice.CharacterApiService
import com.winphyoethu.tpg.core.data.character.remote.model.RemoteCharacterList
import retrofit2.Response
import javax.inject.Inject

/**
 * API for CharacterRemoteDatasource
 *
 * @see CharacterRemoteDatasourceImpl for actual implementation
 *
 */
internal interface CharacterRemoteDatasource {

    /**
     * Get Character by Pagination
     *
     * @param page is the pagination counter
     *
     * @return Response[Response] of RemoteCharacterList[RemoteCharacterList]
     */
    suspend fun getCharacter(page: Int): Response<RemoteCharacterList>

}

/**
 * Implementation of CharacterRemoteDatasource [CharacterRemoteDatasource]
 *
 * @param characterApiService Retrofit Character Api [CharacterApiService]
 */
internal class CharacterRemoteDatasourceImpl @Inject constructor(val characterApiService: CharacterApiService) :
    CharacterRemoteDatasource {

    override suspend fun getCharacter(page: Int): Response<RemoteCharacterList> {
        return characterApiService.getCharacterPagination(page)
    }

}