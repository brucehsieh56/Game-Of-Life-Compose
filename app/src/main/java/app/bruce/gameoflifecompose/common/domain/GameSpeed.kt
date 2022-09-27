package app.bruce.gameoflifecompose.common.domain

import app.bruce.gameoflifecompose.R

enum class GameSpeed(val millis: Int) : GamePreferences<GameSpeed> {
    Slow(1100), Medium(800), Fast(600);

    override fun mapToText(): Int {
        return when (this) {
            Slow -> R.string.game_speed_slow
            Medium -> R.string.game_speed_medium
            Fast -> R.string.game_speed_fast
        }
    }

    override fun getAllValues(): List<GamePreferences<GameSpeed>> {
        return values().toList()
    }

    companion object {
        val default get() = Medium
    }
}