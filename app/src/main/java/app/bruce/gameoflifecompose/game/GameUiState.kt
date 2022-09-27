package app.bruce.gameoflifecompose.game

import kotlin.random.Random

/**
 * UI state for [GameScreen].
 * */
data class GameUiState(
    val isGameStart: Boolean = false,
    val rows: Int = 0,
    val cols: Int = 0,
    val evolutions: Int = 0,
) {
    fun initializeListOfZeros(): List<Int> {
        return List(rows * cols) { 0 }
    }

    fun generateListOfInt(): List<Int> {
        return List(rows * cols) {
            when (Random(System.nanoTime()).nextInt(1, 4)) {
                1 -> random1()
                2 -> random2()
                else -> random3()
            }
        }
    }

    private fun random1() = Random(System.nanoTime()).nextInt(0, 2)
    private fun random2() = if (Random(System.nanoTime()).nextInt(0, 3) < 2) 0 else 1
    private fun random3() = if (Random(System.nanoTime()).nextInt(0, 4) < 3) 0 else 1
}