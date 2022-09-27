package app.bruce.gameoflifecompose.game.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.SkipNext
import androidx.compose.material.icons.rounded.SwitchAccessShortcut
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.bruce.gameoflifecompose.R

@Composable
fun BottomAppBarActions(
    evolutions: Int,
    isButtonEnable: Boolean,
    onGamePause: () -> Unit,
    onGameBoardRefresh: () -> Unit,
    onGameBoardCleanUp: () -> Unit,
    onGameUpdateOnce: () -> Unit,
    onSettingsNavigate: () -> Unit,
) {
    IconButton(
        onClick = {
            onGamePause()
            onSettingsNavigate()
        },
        content = {
            Icon(
                Icons.Rounded.Settings,
                contentDescription = stringResource(R.string.bottom_app_bar_settings)
            )
        }
    )
    IconButton(
        onClick = onGameBoardRefresh,
        enabled = isButtonEnable,
        content = {
            Icon(
                Icons.Rounded.Refresh,
                contentDescription = stringResource(R.string.bottom_app_bar_refresh_grid)
            )
        }
    )
    IconButton(
        onClick = onGameBoardCleanUp,
        enabled = isButtonEnable,
        content = {
            Icon(
                Icons.Rounded.SwitchAccessShortcut,
                contentDescription = stringResource(R.string.bottom_app_bar_empty_grid)
            )
        }
    )
    IconButton(
        onClick = onGameUpdateOnce,
        enabled = isButtonEnable,
        content = {
            Icon(
                Icons.Rounded.SkipNext,
                contentDescription = stringResource(R.string.bottom_app_bar_next)
            )
        }
    )
    Text(
        text = "$evolutions",
        modifier = Modifier.padding(horizontal = 16.dp),
        style = MaterialTheme.typography.titleMedium,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}