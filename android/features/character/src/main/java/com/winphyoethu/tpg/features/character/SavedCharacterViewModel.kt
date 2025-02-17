package com.winphyoethu.tpg.features.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.winphyoethu.tpg.domain.character.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SavedCharacterViewModel @Inject constructor(characterRepository: CharacterRepository) :
    ViewModel() {

    val savedCharacterState = characterRepository.getSavedCharacter().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = persistentListOf()
    )

}