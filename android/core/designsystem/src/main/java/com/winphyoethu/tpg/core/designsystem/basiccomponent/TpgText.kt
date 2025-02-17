package com.winphyoethu.tpg.core.designsystem.basiccomponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.winphyoethu.tpg.core.designsystem.ui.theme.AppTypography
import com.winphyoethu.tpg.core.designsystem.ui.theme.TpgTheme
import com.winphyoethu.tpg.core.designsystem.ui.theme.smallDp
import kotlin.math.max

/**
 * Text component for Extra large title with text size 36 and bold
 */
@Composable
fun TpgXLTitle(
    modifier: Modifier = Modifier,
    title: String,
    textAlign: TextAlign? = null
) {
    Text(
        text = title,
        style = AppTypography.displaySmall.plus(TextStyle(fontWeight = FontWeight.Bold)),
        maxLines = 1,
        modifier = modifier,
        textAlign = textAlign
    )
}

/**
 * Text component for title with text size 24 and bold
 */
@Composable
fun TpgTitle(
    modifier: Modifier = Modifier,
    title: String,
    textAlign: TextAlign? = null
) {
    Text(
        text = title,
        style = AppTypography.headlineSmall.plus(TextStyle(fontWeight = FontWeight.Bold)),
        modifier = modifier,
        textAlign = textAlign
    )
}

/**
 * Text component for Subtitle with text size 16 and bold
 */
@Composable
fun TpgSubTitle(
    modifier: Modifier = Modifier,
    subtitle: String,
    textAlign: TextAlign? = null,
    color: Color = Color.Unspecified
) {
    Text(
        text = subtitle,
        style = AppTypography.titleMedium.plus(TextStyle(fontWeight = FontWeight.Bold)),
        modifier = modifier,
        textAlign = textAlign,
        color = color
    )
}

/**
 * Text component for Body with text size 14
 */
@Composable
fun TpgBody(
    modifier: Modifier = Modifier,
    body: String,
    textAlign: TextAlign? = null,
    color: Color = Color.Unspecified,
    maxLines: Int = Int.MAX_VALUE
) {
    Text(
        text = body,
        style = AppTypography.labelLarge,
        modifier = modifier,
        textAlign = textAlign,
        color = color,
        maxLines = maxLines
    )
}

/**
 * Text component for Label with text size 11
 */
@Composable
fun TpgLabel(
    modifier: Modifier = Modifier,
    label: String,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE
) {
    Text(
        text = label,
        style = AppTypography.labelSmall,
        modifier = modifier,
        textAlign = textAlign,
        maxLines = maxLines
    )
}

@Preview
@Composable
internal fun TpgTextPreview() {
    TpgTheme(darkTheme = true) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(verticalArrangement = Arrangement.spacedBy(smallDp)) {
                Text("Tpg Text", style = AppTypography.displaySmall)
                TpgXLTitle(title = "XL Title - 36.sp")
                TpgTitle(title = "Title - 24.sp")
                TpgSubTitle(subtitle = "Sub Title - 16.sp")
                TpgBody(body = "Body - 14.sp")
                TpgLabel(label = "Label - 11.sp")
            }
        }
    }
}