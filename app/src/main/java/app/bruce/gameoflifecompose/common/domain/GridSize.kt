package app.bruce.gameoflifecompose.common.domain

import app.bruce.gameoflifecompose.R

enum class GridSize(val cols: Int) : GamePreferences<GridSize> {
    XSmall(cols = 8),
    Small(cols = 12),
    Medium(cols = 16),
    Large(cols = 20),
    XLarge(cols = 24),
    X2Large(cols = 28),
    X3Large(cols = 32),
    X4Large(cols = 36),
    X5Large(cols = 40);

    override fun mapToText(): Int {
        return when (this) {
            XSmall -> R.string.grid_size_xsmall
            Small -> R.string.grid_size_small
            Medium -> R.string.grid_size_medium
            Large -> R.string.grid_size_large
            XLarge -> R.string.grid_size_xlarge
            X2Large -> R.string.grid_size_x2large
            X3Large -> R.string.grid_size_x3large
            X4Large -> R.string.grid_size_x4large
            X5Large -> R.string.grid_size_x5large
        }
    }

    override fun getAllValues(): List<GamePreferences<GridSize>> {
        return values().toList()
    }

    companion object {
        val default get() = Medium
    }
}
