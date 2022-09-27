package app.bruce.gameoflifecompose.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cable
import androidx.compose.material.icons.outlined.Grid4x4
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Rule
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import app.bruce.gameoflifecompose.R
import app.bruce.gameoflifecompose.common.domain.GameSpeed
import app.bruce.gameoflifecompose.common.domain.GridSize
import app.bruce.gameoflifecompose.common.domain.GridTheme
import app.bruce.gameoflifecompose.settings.components.GameRuleDialog
import app.bruce.gameoflifecompose.settings.components.SettingsInfo
import app.bruce.gameoflifecompose.settings.components.SettingsRowWithField
import app.bruce.gameoflifecompose.settings.components.SettingsSupport

@ExperimentalMaterial3Api
@Composable
fun SettingsScreen(
    navHostController: NavHostController,
    viewModel: SettingsViewModel = viewModel(),
) {
    val gridSize by viewModel.gridSizeFlow.collectAsState(initial = GridSize.default)
    val gameSpeed by viewModel.gameSpeedFlow.collectAsState(initial = GameSpeed.default)
    val gridTheme by viewModel.gridThemeOptionFlow.collectAsState(initial = GridTheme.default)

    var openGameRuleDialog by remember { mutableStateOf(false) }

    if (openGameRuleDialog) {
        GameRuleDialog(onDismiss = { openGameRuleDialog = false })
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.top_app_bar_title_settings)) },
                navigationIcon = {
                    IconButton(
                        onClick = navHostController::navigateUp,
                        content = {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                contentDescription = stringResource(R.string.top_app_bar_back))
                        }
                    )
                }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .height(60.dp)
                    .clickable { openGameRuleDialog = true },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Rule,
                    contentDescription = stringResource(R.string.settings_screen_game_rule),
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = stringResource(R.string.settings_screen_game_rule),
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Divider()
            SettingsRowWithField(
                gamePreferences = gridSize,
                textTitle = R.string.settings_screen_grid_size,
                imageVector = Icons.Outlined.Grid4x4,
                onClick = { viewModel.onGridSizeUpdate(GridSize.valueOf(it)) }
            )
            SettingsRowWithField(
                gamePreferences = gameSpeed,
                textTitle = R.string.settings_screen_game_speed,
                imageVector = Icons.Outlined.Cable,
                onClick = { viewModel.onGameSpeedUpdate(GameSpeed.valueOf(it)) }
            )
            SettingsRowWithField(
                gamePreferences = gridTheme,
                textTitle = R.string.settings_screen_grid_theme,
                imageVector = Icons.Outlined.Palette,
                onClick = { viewModel.onGridThemeUpdate(GridTheme.valueOf(it)) }
            )

            Divider()
            SettingsSupport()

            Divider()
            SettingsInfo()
        }
    }
}