package app.bruce.gameoflifecompose.common.domain

import androidx.compose.ui.graphics.Color
import app.bruce.gameoflifecompose.R
import app.bruce.gameoflifecompose.ui.theme.*

enum class GridTheme(val gridThemeColor: GridThemeColor) : GamePreferences<GridTheme> {
    Gray(gridThemeColor = GridThemeColor()),
    Sleepy(
        gridThemeColor = GridThemeColor(
            background = Color.Black,
            backgroundSelect = Color(58, 55, 79),
            border = Color.Transparent
        )
    ),
    Terminal(
        gridThemeColor = GridThemeColor(
            background = Color.Black,
            backgroundSelect = Color(58, 253, 71),
            border = Color.Transparent
        )
    ),
    Vivid(
        gridThemeColor = GridThemeColor(
            background = Color(171, 221, 245),
            backgroundSelect = Color(63, 166, 200),
            border = Color.Transparent
        )
    ),
    Original(
        gridThemeColor = GridThemeColor(
            background = md_theme_dark_onPrimary,
            backgroundSelect = md_theme_dark_onPrimaryContainer,
            border = Color.Transparent
        )
    );

    override fun mapToText(): Int {
        return when (this) {
            Gray -> R.string.grid_theme_gray
            Sleepy -> R.string.grid_theme_sleepy
            Terminal -> R.string.grid_theme_terminal
            Vivid -> R.string.grid_theme_vivid
            Original -> R.string.grid_theme_original
        }
    }

    override fun getAllValues(): List<GamePreferences<GridTheme>> {
        return values().toList()
    }

    companion object {
        val default get() = Terminal
    }
}

data class GridThemeColor(
    val background: Color = Color.LightGray,
    val backgroundSelect: Color = Color.DarkGray,
    val border: Color = Color.Gray,
)