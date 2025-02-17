package com.winphyoethu.my_tpg

import androidx.lifecycle.SavedStateHandle
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.winphyoethu.my_tpg.characterdetail.CharacterDetailViewModel
import com.winphyoethu.tpg.core.common.result.TpgResult
import com.winphyoethu.tpg.domain.character.errorcode.CharacterLocalErrorCode
import com.winphyoethu.tpg.domain.character.repository.CharacterRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class CharacterDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val savedStateHandle: SavedStateHandle = mock()
    private val characterRepository: CharacterRepository = mock()
    private lateinit var characterDetailViewModel: CharacterDetailViewModel

    @Before
    fun setUp() {
        characterDetailViewModel = CharacterDetailViewModel(savedStateHandle, characterRepository)
    }

    @Test
    fun `Save Character Emit Success Message`() {
        runTest {
            var value = 0
            backgroundScope.launch(UnconfinedTestDispatcher()) {
                characterDetailViewModel.characterActionStatus.collect {
                    value = it
                }
            }

            whenever(characterRepository.saveCharacter(any())).thenReturn(TpgResult.Success(1))

            characterDetailViewModel.saveCharacter("")

            Assert.assertEquals(R.string.character_save_success, value)
        }
    }

    @Test
    fun `Save Character Emit Error Message`() {
        runTest {
            var value = 0
            backgroundScope.launch(UnconfinedTestDispatcher()) {
                characterDetailViewModel.characterActionStatus.collect {
                    value = it
                }
            }

            whenever(characterRepository.saveCharacter(any())).thenReturn(TpgResult.Error(CharacterLocalErrorCode.SaveCharacterError("Save Character Error")))

            characterDetailViewModel.saveCharacter("")

            Assert.assertEquals(R.string.character_save_fail, value)
        }
    }

    @Test
    fun `Delete Character Emit Success Message`() {
        runTest {
            var value = 0
            backgroundScope.launch(UnconfinedTestDispatcher()) {
                characterDetailViewModel.characterActionStatus.collect {
                    value = it
                }
            }

            whenever(characterRepository.deleteCharacter(any())).thenReturn(TpgResult.Success(1))

            characterDetailViewModel.deleteCharacter("")

            Assert.assertEquals(R.string.character_remove_success, value)
        }
    }

    @Test
    fun `Delete Character Emit Error Message`() {
        runTest {
            var value = 0
            backgroundScope.launch(UnconfinedTestDispatcher()) {
                characterDetailViewModel.characterActionStatus.collect {
                    value = it
                }
            }

            whenever(characterRepository.deleteCharacter(any())).thenReturn(TpgResult.Error(CharacterLocalErrorCode.DeleteCharacterError("Delete Character Error")))

            characterDetailViewModel.deleteCharacter("")

            Assert.assertEquals(R.string.character_remove_fail, value)
        }
    }

}