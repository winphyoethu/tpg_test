package com.winphyoethu.tpg.features.character.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.winphyoethu.tpg.core.model.character.Character
import com.winphyoethu.tpg.features.character.CharacterListRoute
import kotlinx.serialization.Serializable

@Serializable
data object CharacterListScreen

fun NavController.navigateToCharacterList(navOptions: NavOptions?) {
    navigate(CharacterListScreen, navOptions)
}

fun NavGraphBuilder.characterListScreen(
    characterClick: (character: Character) -> Unit,
    savedClick: () -> Unit
) {
    composable<CharacterListScreen> {
        CharacterListRoute(characterClick = characterClick, savedClick = savedClick)
    }
}