package app.bruce.gameoflifecompose.game

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import app.bruce.gameoflifecompose.common.domain.GridSize
import app.bruce.gameoflifecompose.common.preferences.SettingsPreferences
import app.bruce.gameoflifecompose.common.preferences.dataStore
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class GameViewModel(app: Application) : AndroidViewModel(app) {

    private val settingsPreferences = SettingsPreferences(app.dataStore)
    private val gameUiStateFlow = MutableStateFlow(GameUiState())
    private val gridSizeFlow = settingsPreferences.gridSizeFlow
    val gridThemeOptionFlow = settingsPreferences.gridThemeFlow

    val uiState = combine(gridSizeFlow, gameUiStateFlow) { g: GridSize, _: GameUiState ->
        // Update gameUiStateFlow
        gameUiStateFlow.value = gameUiStateFlow.value.copy(cols = g.cols)

        // Return gameUiStateFlow
        gameUiStateFlow.value
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), GameUiState())

    private val _board = mutableStateListOf<Int>()
    val board: List<Int> = _board

    private var rows = gameUiStateFlow.value.rows
    private var cols = gameUiStateFlow.value.cols
    private var gameJob: Job? = null

    fun onRowsUpdate(updatedRows: Int) {
        gameUiStateFlow.value = gameUiStateFlow.value.copy(rows = updatedRows)
        rows = gameUiStateFlow.value.rows
        cols = gameUiStateFlow.value.cols
        onGameBoardRefresh()
    }

    fun onStart() {
        gameJob = viewModelScope.launch {
            val gameSpeed = settingsPreferences.gameSpeedFlow.first().millis.toLong()
            gameUiStateFlow.value = gameUiStateFlow.value.copy(isGameStart = true)
            try {
                while (isActive) {
                    onGameUpdateOnce()
                    delay(gameSpeed)
                }
            } catch (t: Throwable) {
                throw t
            } finally {
                gameUiStateFlow.value = gameUiStateFlow.value.copy(isGameStart = false)
            }
        }
    }

    fun onGameUpdateOnce() {
        // Update board
        _board.apply {
            val updatedBoard = computeNextGeneration(board.toMutableList())
            clear()
            addAll(updatedBoard)
        }

        // Update evolution counts
        gameUiStateFlow.update {
            it.copy(evolutions = it.evolutions + 1)
        }
    }

    fun onGameBoardRefresh() {
        _board.apply {
            clear()
            addAll(gameUiStateFlow.value.generateListOfInt())
        }

        gameUiStateFlow.update {
            it.copy(isGameStart = false, evolutions = 0)
        }
    }

    fun onGamePause() {
        gameJob?.cancel()

        gameUiStateFlow.update {
            it.copy(isGameStart = false)
        }
    }

    fun onGameBoardCleanUp() {
        _board.apply {
            clear()
            addAll(gameUiStateFlow.value.initializeListOfZeros())
        }

        gameUiStateFlow.update {
            it.copy(isGameStart = false, evolutions = 0)
        }
    }

    fun onSingleCellFlip(index: Int) {
        _board[index] = 1 - _board[index]
    }

    /**
     * Game Logic.
     * */
    private fun computeNextGeneration(board: MutableList<Int>): List<Int> {
        for (i in board.indices) {
            val nei = board.countNeighbors(i, rows, cols)
            if (board[i] == 1) {
                if (nei in 2..3) {
                    board[i] = 3
                }
            } else if (nei == 3) {
                board[i] = 2
            }
        }

        for (i in board.indices) {
            if (board[i] == 1) board[i] = 0
            else if (board[i] in 2..3) board[i] = 1
        }
        return board.toList()
    }

    private fun List<Int>.countNeighbors(position: Int, rows: Int, cols: Int): Int {
        val validNeighborIndices = validNeighborIndices(position, rows, cols)
        var nei = 0
        for (index in validNeighborIndices) {
            if (this[index] in listOf(1, 3)) {
                nei += 1
            }
        }
        return nei
    }

    private fun validNeighborIndices(position: Int, rows: Int, cols: Int): List<Int> {
        val row = position / cols
        val col = position % cols

        /**
         * (row, col) for 8 neighbors
         * */
        val neighborIndices = listOf(
            row to col - 1,
            row - 1 to col - 1,
            row - 1 to col,
            row - 1 to col + 1,
            row to col + 1,
            row + 1 to col + 1,
            row + 1 to col,
            row + 1 to col - 1,
        ).filter {
            it.first in 0 until rows && it.second in 0 until cols
        }.map {
            cols * it.first + it.second
        }

        return neighborIndices
    }
}