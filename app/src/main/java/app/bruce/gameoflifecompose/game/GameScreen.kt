package app.bruce.gameoflifecompose.game

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import app.bruce.gameoflifecompose.R
import app.bruce.gameoflifecompose.common.domain.GridTheme
import app.bruce.gameoflifecompose.common.routing.Router
import app.bruce.gameoflifecompose.game.components.BottomAppBarActions
import app.bruce.gameoflifecompose.game.components.GameGridByCanvas

@ExperimentalMaterial3Api
@Composable
fun GameScreen(navHostController: NavHostController, viewModel: GameViewModel = viewModel()) {

    val view = LocalView.current
    val configuration = LocalConfiguration.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val uiState by viewModel.uiState.collectAsState(initial = GameUiState())
    val gameBoard = viewModel.board
    val gridTheme by viewModel.gridThemeOptionFlow.collectAsState(initial = GridTheme.default)

    var columnHeight by remember { mutableStateOf(1) }

    val screenWidthDp = configuration.screenWidthDp.dp
    val cols = uiState.cols
    val rows = uiState.rows
    val cellUnitWidth = screenWidthDp / cols
    val cellUnitWidthPx = LocalDensity.current.run { cellUnitWidth.toPx() }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            // Stop game when onStop()
            if (event == Lifecycle.Event.ON_STOP) viewModel.onGamePause()
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            view.keepScreenOn = false
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomAppBar(
                actions = {
                    BottomAppBarActions(
                        evolutions = uiState.evolutions,
                        isButtonEnable = !uiState.isGameStart,
                        onGamePause = viewModel::onGamePause,
                        onGameBoardRefresh = viewModel::onGameBoardRefresh,
                        onGameBoardCleanUp = viewModel::onGameBoardCleanUp,
                        onGameUpdateOnce = viewModel::onGameUpdateOnce,
                        onSettingsNavigate = {
                            navHostController.navigate(Router.SettingsScreen.route)
                        }
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            if (uiState.isGameStart) {
                                viewModel.onGamePause()
                                view.keepScreenOn = false
                            } else {
                                viewModel.onStart()
                                view.keepScreenOn = true
                            }
                        },
                        content = {
                            Icon(
                                imageVector = if (uiState.isGameStart) {
                                    Icons.Rounded.Pause
                                } else {
                                    Icons.Rounded.PlayArrow
                                },
                                contentDescription = stringResource(R.string.bottom_app_bar_start)
                            )
                        }
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .onGloballyPositioned {
                    // Use Column height and width to calculate the number of rows and padding
                    if (uiState.rows <= 0) {
                        columnHeight = it.size.height
                        val desiredRows = it.size.height * cols / it.size.width
                        viewModel.onRowsUpdate(updatedRows = desiredRows)
                    }
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            if (rows != 0 && gameBoard.isNotEmpty()) {
                val topPadding = (columnHeight - rows * cellUnitWidthPx).dp / 2
                GameGridByCanvas(
                    modifier = Modifier.padding(top = topPadding),
                    cols = cols,
                    rows = rows,
                    gameBoard = gameBoard,
                    cellUnitWidth = cellUnitWidth,
                    gridThemeColor = gridTheme.gridThemeColor,
                    onCellClick = viewModel::onSingleCellFlip
                )
            }
        }
    }
}