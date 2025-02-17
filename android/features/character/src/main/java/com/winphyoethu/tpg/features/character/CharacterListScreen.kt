package com.winphyoethu.tpg.features.character

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.winphyoethu.tpg.core.designsystem.basiccomponent.TpgTitle
import com.winphyoethu.tpg.core.designsystem.tpgcomponent.CharacterCard
import com.winphyoethu.tpg.core.designsystem.ui.theme.TpgIcons
import com.winphyoethu.tpg.core.designsystem.ui.theme.TpgTheme
import com.winphyoethu.tpg.core.designsystem.ui.theme.mediumDp
import com.winphyoethu.tpg.core.designsystem.ui.theme.smallDp
import com.winphyoethu.tpg.core.model.character.Character
import kotlinx.coroutines.flow.flowOf

@Composable
internal fun CharacterListRoute(
    characterClick: (character: Character) -> Unit,
    savedClick: () -> Unit,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    val characters = viewModel.characterFlow.collectAsLazyPagingItems()

    CharacterListScreen(
        characters = characters,
        characterClick = characterClick,
        savedClick = savedClick
    )
}

@Composable
internal fun CharacterListScreen(
    characters: LazyPagingItems<Character>,
    characterClick: (character: Character) -> Unit,
    savedClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(mediumDp)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(mediumDp)) {
            TpgTitle(
                title = "Rick & Morty Characters",
                modifier = Modifier.align(Alignment.CenterStart)
            )
            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .background(MaterialTheme.colorScheme.primary, CircleShape),
                onClick = {
                    savedClick()
                }) {
                Icon(
                    imageVector = TpgIcons.Saved,
                    contentDescription = "Saved Character",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

        LazyVerticalGrid(
            GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(smallDp),
            horizontalArrangement = Arrangement.spacedBy(smallDp),
            modifier = Modifier.fillMaxSize()
        ) {

            items(count = characters.itemCount, key = characters.itemKey { it.id }) { index ->
                characters[index]?.let {
                    CharacterCard(it) { character ->
                        characterClick(character)
                    }
                }
            }

            when (characters.loadState.refresh) {
                is LoadState.Error -> {
                    //state.error to get error message
                }

                is LoadState.Loading -> { // Loading UI
                    item(span = { GridItemSpan(2) }) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(8.dp),
                                text = "Refresh Loading"
                            )

                            CircularProgressIndicator()
                        }
                    }
                }

                else -> {}
            }

            when (characters.loadState.append) {
                is LoadState.Error -> {
                    //state.error to get error message
                }

                is LoadState.Loading -> { // Pagination Loading UI
                    item(span = { GridItemSpan(2) }) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(text = "Pagination Loading")

                            CircularProgressIndicator()
                        }
                    }
                }

                else -> {}
            }

        }
    }
}

@Preview
@Composable
private fun CharacterListScreenPreview() {
    TpgTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            CharacterListScreen(
                flowOf(
                    PagingData.from(
                        listOf<Character>()
//                        listOf(
//                            mockCharacter,
//                            mockCharacter.copy(id = 2)
//                        )
                    )
                ).collectAsLazyPagingItems(),
                characterClick = {

                }, savedClick = {

                }
            )
        }
    }
}