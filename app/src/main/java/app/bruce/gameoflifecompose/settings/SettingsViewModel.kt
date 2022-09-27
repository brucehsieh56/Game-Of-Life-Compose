package app.bruce.gameoflifecompose.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import app.bruce.gameoflifecompose.common.domain.GameSpeed
import app.bruce.gameoflifecompose.common.domain.GridSize
import app.bruce.gameoflifecompose.common.domain.GridTheme
import app.bruce.gameoflifecompose.common.preferences.SettingsPreferences
import app.bruce.gameoflifecompose.common.preferences.dataStore
import kotlinx.coroutines.launch

class SettingsViewModel(app: Application) : AndroidViewModel(app) {

    private val settingsPreferences = SettingsPreferences(app.dataStore)
    val gridSizeFlow = settingsPreferences.gridSizeFlow
    val gameSpeedFlow = settingsPreferences.gameSpeedFlow
    val gridThemeOptionFlow = settingsPreferences.gridThemeFlow

    fun onGridSizeUpdate(gridSize: GridSize) {
        viewModelScope.launch {
            try {
                settingsPreferences.updateGridSize(gridSize)
            } catch (t: Throwable) {
                throw t
            }
        }
    }

    fun onGameSpeedUpdate(gameSpeed: GameSpeed) {
        viewModelScope.launch {
            try {
                settingsPreferences.updateGameSpeed(gameSpeed)
            } catch (t: Throwable) {
                throw t
            }
        }
    }

    fun onGridThemeUpdate(gridTheme: GridTheme) {
        viewModelScope.launch {
            try {
                settingsPreferences.updateGridTheme(gridTheme)
            } catch (t: Throwable) {
                throw t
            }
        }
    }
}