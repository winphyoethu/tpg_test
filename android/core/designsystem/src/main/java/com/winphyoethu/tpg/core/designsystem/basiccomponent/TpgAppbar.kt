package com.winphyoethu.tpg.core.designsystem.basiccomponent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.winphyoethu.tpg.core.designsystem.ui.theme.TpgIcons
import com.winphyoethu.tpg.core.designsystem.ui.theme.TpgTheme
import com.winphyoethu.tpg.core.designsystem.ui.theme.mediumDp
import com.winphyoethu.tpg.core.designsystem.ui.theme.xxLargeDp

/**
 * Custom Appbar to be used in Tpg app screens
 */
@Composable
fun TpgAppbar(
    icon: ImageVector? = null,
    iconDescription: String? = null,
    iconAction: (() -> Unit)? = null,
    title: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(mediumDp)
    ) {
        icon?.let {
            Icon(
                imageVector = icon,
                contentDescription = iconDescription,
                modifier = Modifier
                    .size(xxLargeDp)
                    .clickable { iconAction?.invoke() }
                    .semantics {
                        this.contentDescription = "Navigate Back"
                        onClick { true }
                    }
            )
        }
        TpgSubTitle(subtitle = title, modifier = Modifier.padding(start = mediumDp))
    }
}

@Preview
@Composable
private fun TpgAppbarPreview() {
    TpgTheme {
        Surface {
            TpgAppbar(
                icon = TpgIcons.Back,
                iconDescription = "Back Button",
                iconAction = { },
                title = "Dashboard"
            )
        }
    }
}