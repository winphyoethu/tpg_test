package com.winphyoethu.tpg.core.data.character

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.winphyoethu.tpg.core.common.result.TpgResult
import com.winphyoethu.tpg.core.data.character.local.CharacterLocalDatasource
import com.winphyoethu.tpg.core.data.character.remote.datasource.CharacterRemoteDatasource
import com.winphyoethu.tpg.core.data.character.remote.model.mockRemoteCharacterList
import com.winphyoethu.tpg.core.database.entity.mockCharacterEntity
import com.winphyoethu.tpg.domain.character.errorcode.CharacterErrorCode.*
import com.winphyoethu.tpg.domain.character.errorcode.CharacterLocalErrorCode
import com.winphyoethu.tpg.domain.character.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.exceptions.base.MockitoException
import retrofit2.Response
import kotlin.test.assertEquals
import kotlin.test.assertIs

internal class CharacterRepositoryTest {

    private val remoteDatasource = mock<CharacterRemoteDatasource>()
    private val localDatasource = mock<CharacterLocalDatasource>()
    private lateinit var CharacterRepository: CharacterRepository
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Before
    fun setUp() {
        CharacterRepository = CharacterRepositoryImpl(
            remoteDatasource,
            localDatasource,
            moshi,
            Dispatchers.Unconfined
        )
    }

    @Test
    fun `Get Character List return Success`() {
        runTest {
            whenever(remoteDatasource.getCharacter(any())).thenReturn(
                Response.success(mockRemoteCharacterList)
            )

            val result = CharacterRepository.getCharacter(1)

            assertEquals(TpgResult.Success::class.simpleName, result::class.simpleName)
        }
    }

    @Test
    fun `Get Character List return Authentication Error`() {
        runTest {
            whenever(remoteDatasource.getCharacter(any())).thenReturn(
                Response.error(401, "Authentication Error".toResponseBody())
            )

            val result = CharacterRepository.getCharacter(1)

            assertEquals(TpgResult.Error(AuthenticationError("Authentication Error")), result)
        }
    }

    @Test
    fun `Get Character List return Unknown Error`() {
        runTest {
            whenever(remoteDatasource.getCharacter(any())).thenReturn(
                Response.error(500, "Unknown Error".toResponseBody())
            )

            val result = CharacterRepository.getCharacter(1)

            assertEquals(TpgResult.Error(UnknownError("Unknown Error")), result)
        }
    }

    @Test
    fun `Get Character List return Unexpected Error`() {
        runTest {
            whenever(remoteDatasource.getCharacter(any())).doThrow(MockitoException("Unexpected Error"))

            val result = CharacterRepository.getCharacter(1)

            assertEquals(TpgResult.Error(UnexpectedError("Unexpected Error")), result)
        }
    }

    @Test
    fun `Get Character By Id return Success`() {
        runTest {
            whenever(localDatasource.getCharacterById(any())).thenReturn(mockCharacterEntity)

            val result = CharacterRepository.getCharacterById(1)

            assertEquals(TpgResult.Success::class.simpleName, result::class.simpleName)
        }
    }

    @Test
    fun `Get Character By Id return Error`() {
        runTest {
            whenever(localDatasource.getCharacterById(any())).thenReturn(null)

            val result = CharacterRepository.getCharacterById(1)

            assertIs<TpgResult.Error<CharacterLocalErrorCode.GetCharacterByIdError>>(result)
        }
    }

    @Test
    fun `Get Character By Id throw Exception`() {
        runTest {
            whenever(localDatasource.getCharacterById(any())).doThrow(MockitoException("Exception"))

            val result = CharacterRepository.getCharacterById(1)

            assertIs<TpgResult.Error<CharacterLocalErrorCode.GetCharacterByIdError>>(result)
        }
    }

    @Test
    fun `Save Character return Success`() {
        runTest {
            whenever(
                localDatasource.saveCharacter(
                    any(),
                    any(),
                    any(),
                    any(),
                    any()
                )
            ).thenReturn(1L)

            val result = CharacterRepository.saveCharacter(mockResponse)

            assertEquals(TpgResult.Success::class.simpleName, result::class.simpleName)
        }
    }

    @Test
    fun `Save Character throw Exception`() {
        runTest {
            whenever(
                localDatasource.saveCharacter(
                    any(),
                    any(),
                    any(),
                    any(),
                    any()
                )
            ).thenReturn(1L)

            val result = CharacterRepository.saveCharacter("")

            assertIs<TpgResult.Error<CharacterLocalErrorCode.SaveCharacterError>>(result)
        }
    }

    @Test
    fun `Delete Character return Success`() {
        runTest {
            whenever(localDatasource.deleteCharacter(any())).thenReturn(1)

            val result = CharacterRepository.deleteCharacter(mockResponse)

            assertEquals(TpgResult.Success::class.simpleName, result::class.simpleName)
        }
    }

    @Test
    fun `Delete Character throw Exception`() {
        runTest {
            whenever(localDatasource.deleteCharacter(any())).thenReturn(1)

            val result = CharacterRepository.deleteCharacter("")

            assertIs<TpgResult.Error<CharacterLocalErrorCode.DeleteCharacterError>>(result)
        }
    }

}

val mockResponse = "{" +
        "\"id\": 1," +
        "\"name\": \"Rick Sanchez\"," +
        "\"status\": \"Alive\"," +
        "\"species\": \"Human\"," +
        "\"type\": \"\"," +
        "\"gender\": \"Male\"," +
        "\"origin\": {" +
        "\"name\": \"Earth (C-137)\"," +
        "\"url\": \"https://rickandmortyapi.com/api/location/1\"" +
        "}," +
        "\"location\": {" +
        "\"name\": \"Citadel of Ricks\"," +
        "\"url\": \"https://rickandmortyapi.com/api/location/3\"" +
        "}," +
        "\"image\": \"https://rickandmortyapi.com/api/character/avatar/1.jpeg\"," +
        "\"episode\": [" +
        "\"https://rickandmortyapi.com/api/episode/1\"," +
        "\"https://rickandmortyapi.com/api/episode/2\"," +
        "\"https://rickandmortyapi.com/api/episode/3\"," +
        "\"https://rickandmortyapi.com/api/episode/4\"," +
        "\"https://rickandmortyapi.com/api/episode/5\"," +
        "\"https://rickandmortyapi.com/api/episode/6\"" +
        "]," +
        "\"url\": \"https://rickandmortyapi.com/api/character/1\"," +
        "\"created\": \"2017-11-04T18:48:46.250Z\"" +
        "}"