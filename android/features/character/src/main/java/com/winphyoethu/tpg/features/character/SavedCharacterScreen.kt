package com.winphyoethu.tpg.features.character

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.winphyoethu.tpg.core.designsystem.basiccomponent.TpgAppbar
import com.winphyoethu.tpg.core.designsystem.basiccomponent.TpgBody
import com.winphyoethu.tpg.core.designsystem.basiccomponent.TpgButton
import com.winphyoethu.tpg.core.designsystem.tpgcomponent.CharacterCard
import com.winphyoethu.tpg.core.designsystem.ui.theme.TpgIcons
import com.winphyoethu.tpg.core.designsystem.ui.theme.TpgTheme
import com.winphyoethu.tpg.core.designsystem.ui.theme.mediumDp
import com.winphyoethu.tpg.core.designsystem.ui.theme.smallDp
import com.winphyoethu.tpg.core.model.character.Character
import com.winphyoethu.tpg.core.model.character.mockCharacter
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun SavedCharacterRoute(
    characterClick: (character: Character) -> Unit,
    backClick: () -> Unit,
    viewModel: SavedCharacterViewModel = hiltViewModel()
) {
    val characters by viewModel.savedCharacterState.collectAsStateWithLifecycle()

    SavedCharacterScreen(
        characters = characters,
        characterClick = characterClick,
        backClick = backClick
    )
}

@Composable
internal fun SavedCharacterScreen(
    characters: PersistentList<Character>,
    characterClick: (character: Character) -> Unit,
    backClick: () -> Unit
) {
    Column {
        TpgAppbar(
            title = "Saved Characters",
            icon = TpgIcons.Back,
            iconDescription = "Back",
            iconAction = {
                backClick()
            })
        if (characters.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                TpgBody(
                    modifier = Modifier
                        .padding(mediumDp),
                    body = "No Character has been saved yet!"
                )
                TpgButton(text = "See Characters") {
                    backClick()
                }
            }
        } else {
            LazyVerticalGrid(
                GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(smallDp),
                horizontalArrangement = Arrangement.spacedBy(smallDp),
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(items = characters, key = { it.id }) { character ->
                    CharacterCard(character) {
                        characterClick(character)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun SavedCharacterScreenPreview() {
    TpgTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            SavedCharacterScreen(
                characters = persistentListOf(
mockCharacter
                ), characterClick = { character ->

                },
                backClick = {

                }
            )
        }
    }
}