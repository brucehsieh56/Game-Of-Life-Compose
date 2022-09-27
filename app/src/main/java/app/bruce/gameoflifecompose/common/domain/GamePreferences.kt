package app.bruce.gameoflifecompose.common.domain

interface GamePreferences<T : Enum<T>> {
    fun mapToText(): Int
    fun getAllValues(): List<GamePreferences<T>>
}