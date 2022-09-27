package app.bruce.gameoflifecompose.common.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import app.bruce.gameoflifecompose.common.domain.GameSpeed
import app.bruce.gameoflifecompose.common.domain.GridSize
import app.bruce.gameoflifecompose.common.domain.GridTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private const val DATA_STORE_NAME = "DATA_STORE_NAME"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

class SettingsPreferences(private val dataStore: DataStore<Preferences>) {

    private object PreferencesKeys {
        val GRID_COLS = stringPreferencesKey("GRID_COLS")
        val GAME_SPEED = stringPreferencesKey("GAME_SPEED")
        val GRID_THEME = stringPreferencesKey("GRID_THEME")
    }

    val gridSizeFlow: Flow<GridSize> = dataStore.data
        .catch { t ->
            if (t is IOException) {
                emit(emptyPreferences())
            } else {
                throw t
            }
        }
        .map { preferences ->
            val gridSize = GridSize.valueOf(
                preferences[PreferencesKeys.GRID_COLS] ?: GridSize.default.name
            )
            gridSize
        }

    val gameSpeedFlow: Flow<GameSpeed> = dataStore.data
        .catch { t ->
            if (t is IOException) {
                emit(emptyPreferences())
            } else {
                throw t
            }
        }
        .map { preferences ->
            val gameSpeed = GameSpeed.valueOf(
                preferences[PreferencesKeys.GAME_SPEED] ?: GameSpeed.Medium.name
            )
            gameSpeed
        }

    val gridThemeFlow: Flow<GridTheme> = dataStore.data
        .catch { t ->
            if (t is IOException) {
                emit(emptyPreferences())
            } else {
                throw t
            }
        }
        .map { preferences ->
            val gridTheme = GridTheme.valueOf(
                preferences[PreferencesKeys.GRID_THEME] ?: GridTheme.default.name
            )
            gridTheme
        }

    suspend fun updateGridSize(gridSize: GridSize) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.GRID_COLS] = gridSize.name
        }
    }

    suspend fun updateGameSpeed(gameSpeed: GameSpeed) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.GAME_SPEED] = gameSpeed.name
        }
    }

    suspend fun updateGridTheme(gridTheme: GridTheme) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.GRID_THEME] = gridTheme.name
        }
    }
}