package com.winphyoethu.tpg.core.designsystem.tpgcomponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.winphyoethu.tpg.core.designsystem.R
import com.winphyoethu.tpg.core.designsystem.basiccomponent.TpgBody
import com.winphyoethu.tpg.core.designsystem.basiccomponent.TpgLabel
import com.winphyoethu.tpg.core.designsystem.ui.theme.TpgTheme
import com.winphyoethu.tpg.core.designsystem.ui.theme.characterImageSize
import com.winphyoethu.tpg.core.designsystem.ui.theme.largeDp
import com.winphyoethu.tpg.core.designsystem.ui.theme.mediumDp
import com.winphyoethu.tpg.core.model.character.Character
import com.winphyoethu.tpg.core.model.character.mockCharacter

/**
 * Character Card to be used in Character List and Saved Character Screen
 */
@Composable
fun CharacterCard(character: Character, characterClick: (character: Character) -> Unit) {
    Card(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .semantics(mergeDescendants = true) {
                contentDescription = character.name
            },
        shape = RoundedCornerShape(largeDp),
        onClick = {
            characterClick(character)
        }) {
        Column(
            verticalArrangement = Arrangement.spacedBy(mediumDp),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(character.image)
                    .placeholder(R.drawable.rickandmorty)
                    .build(),
                contentScale = ContentScale.FillBounds,
                contentDescription = character.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(characterImageSize)
                    .clip(RoundedCornerShape(largeDp))
            )
            TpgBody(
                body = character.name,
                modifier = Modifier.padding(start = mediumDp, end = mediumDp),
                maxLines = 1
            )
            TpgLabel(
                label = character.origin,
                modifier = Modifier.padding(start = mediumDp, end = mediumDp, bottom = mediumDp),
                maxLines = 1
            )
        }
    }
}

@Preview
@Composable
private fun CharacterCardPreview() {
    TpgTheme {
        Surface {
            CharacterCard(mockCharacter) {

            }
        }
    }
}