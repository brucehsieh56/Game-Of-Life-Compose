package app.bruce.gameoflifecompose.game.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.bruce.gameoflifecompose.common.domain.GridThemeColor

@Composable
fun CellUnit(
    width: Dp,
    item: Int,
    gridThemeColor: GridThemeColor,
    onCellClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(width)
            .background(if (item == 0) gridThemeColor.background else gridThemeColor.backgroundSelect)
            .border(width = 0.2.dp, color = gridThemeColor.border)
            .clickable {
                onCellClick()
            }
    ) {
    }
}