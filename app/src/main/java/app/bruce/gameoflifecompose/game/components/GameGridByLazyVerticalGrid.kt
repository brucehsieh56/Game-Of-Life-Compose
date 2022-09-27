package app.bruce.gameoflifecompose.game.components

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import app.bruce.gameoflifecompose.common.domain.GridThemeColor

/**
 * Use [LazyVerticalGrid] to display [CellUnit]s on grid.
 * */
@Composable
fun GameGridByLazyVerticalGrid(
    cols: Int,
    rows: Int,
    gameBoard: List<Int>,
    cellUnitWidth: Dp,
    gridThemeColor: GridThemeColor,
    onCellClick: (Int) -> Unit,
) {
    LazyVerticalGrid(columns = GridCells.Fixed(cols)) {
        items(
            items = gameBoard.mapIndexed { index, i -> index to i },
            key = { item -> item.first }
        ) { item ->
            CellUnit(
                width = cellUnitWidth,
                item = item.second,
                gridThemeColor = gridThemeColor,
                onCellClick = { onCellClick(item.first) }
            )
        }
    }
}