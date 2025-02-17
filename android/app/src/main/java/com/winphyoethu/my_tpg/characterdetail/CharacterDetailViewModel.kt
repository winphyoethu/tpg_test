package com.winphyoethu.my_tpg.characterdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.winphyoethu.my_tpg.R
import com.winphyoethu.tpg.core.common.result.TpgResult
import com.winphyoethu.tpg.domain.character.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val characterRepository: CharacterRepository
) : ViewModel() {

    private val characterId: Int = savedStateHandle["characterId"] ?: 0

    private val _characterStatus: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val characterStatus = _characterStatus.onStart { getCharacter() }.shareIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        replay = 1
    )

    fun getCharacter() {
        viewModelScope.launch {
            val result = characterRepository.getCharacterById(characterId)

            if (result is TpgResult.Success) {
                _characterStatus.emit(true)
            } else {
                _characterStatus.emit(false)
            }
        }
    }

    private val _characterActionStatus: MutableSharedFlow<Int> = MutableSharedFlow()
    val characterActionStatus = _characterActionStatus.shareIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L)
    )

    fun saveCharacter(characterString: String) {
        viewModelScope.launch {
            val result = characterRepository.saveCharacter(characterString)

            if (result is TpgResult.Success) {
                _characterActionStatus.emit(R.string.character_save_success)
            } else {
                _characterActionStatus.emit(R.string.character_save_fail)
            }
        }
    }

    fun deleteCharacter(characterString: String) {
        viewModelScope.launch {
            val result = characterRepository.deleteCharacter(characterString)

            if (result is TpgResult.Success) {
                _characterActionStatus.emit(R.string.character_remove_success)
            } else {
                _characterActionStatus.emit(R.string.character_remove_fail)
            }
        }
    }

}