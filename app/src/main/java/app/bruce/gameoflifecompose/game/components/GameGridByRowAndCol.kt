package app.bruce.gameoflifecompose.game.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import app.bruce.gameoflifecompose.common.domain.GridThemeColor

/**
 * Use [Row] and [Column] to draw grid and display [CellUnit]s.
 * */
@Composable
fun GameGridByRowAndCol(
    cols: Int,
    rows: Int,
    gameBoard: List<Int>,
    cellUnitWidth: Dp,
    gridThemeColor: GridThemeColor,
    onCellClick: (Int) -> Unit,
) {
    for (row in 0 until rows) {
        Row {
            for (col in 0 until cols) {
                val index = row * cols + col
                Column {
                    CellUnit(
                        width = cellUnitWidth,
                        item = gameBoard[index],
                        gridThemeColor = gridThemeColor,
                        onCellClick = { onCellClick(index) }
                    )
                }
            }
        }
    }
}