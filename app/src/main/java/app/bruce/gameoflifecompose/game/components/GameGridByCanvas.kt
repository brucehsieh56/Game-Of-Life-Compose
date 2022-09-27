package app.bruce.gameoflifecompose.game.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import app.bruce.gameoflifecompose.common.domain.GridThemeColor

@Composable
fun GameGridByCanvas(
    modifier: Modifier,
    cols: Int,
    rows: Int,
    gameBoard: List<Int>,
    cellUnitWidth: Dp,
    gridThemeColor: GridThemeColor,
    onCellClick: (Int) -> Unit,
) {
    val widthInPx = LocalDensity.current.run { cellUnitWidth.toPx() }
    Canvas(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(true) {
                detectTapGestures { tapPosition ->
                    val col = (tapPosition.x / widthInPx).toInt()
                    val row = (tapPosition.y / widthInPx).toInt()
                    val index = row * cols + col
                    onCellClick(index)
                }
            }
    ) {
        for (row in 0 until rows) {
            for (col in 0 until cols) {
                val index = row * cols + col
                drawRect(
                    color = if (gameBoard[index] == 0) {
                        gridThemeColor.background
                    } else {
                        gridThemeColor.backgroundSelect
                    },
                    topLeft = Offset(x = widthInPx * (col % cols), y = widthInPx * row),
                    size = Size(widthInPx, widthInPx)
                )
            }
        }
    }
}