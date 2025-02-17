package com.winphyoethu.tpg.features.character.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.winphyoethu.tpg.core.model.character.Character
import com.winphyoethu.tpg.features.character.SavedCharacterRoute
import kotlinx.serialization.Serializable

@Serializable
data object SavedCharacterScreen

fun NavController.navigateToSavedCharacterList(navOptions: NavOptions? = null) {
    navigate(SavedCharacterScreen, navOptions)
}

fun NavGraphBuilder.savedCharacterScreen(
    characterClick: (character: Character) -> Unit,
    backClick: () -> Unit
) {
    composable<SavedCharacterScreen> {
        SavedCharacterRoute(characterClick = characterClick, backClick = backClick)
    }
}