package com.winphyoethu.tpg.core.data.character.remote.apiservice

import com.winphyoethu.tpg.core.data.character.remote.model.RemoteCharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Character Api Service
 */
interface CharacterApiService {

    @GET("character")
    suspend fun getCharacterPagination(
        @Query("page") page: Int
    ): Response<RemoteCharacterList>

}