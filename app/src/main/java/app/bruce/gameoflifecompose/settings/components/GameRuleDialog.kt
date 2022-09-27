package app.bruce.gameoflifecompose.settings.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.bruce.gameoflifecompose.R

@Composable
fun GameRuleDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            FilledTonalButton(
                onClick = onDismiss,
                content = {
                    Text(text = stringResource(R.string.game_rule_dialog_confirm))
                }
            )
        },
        title = { Text(text = stringResource(R.string.game_rule_dialog_title)) },
        text = {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.game_rule_cell_with_eight_neighbors),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(200.dp)
                )
                Text(
                    text = stringResource(R.string.game_rule_dialog_explain_1),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Spacer(modifier = Modifier.height(16.dp))

                Image(
                    painter = painterResource(R.drawable.game_rule_cell_survive_rule),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(200.dp)
                )
                Text(
                    text = stringResource(R.string.game_rule_dialog_explain_2),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Spacer(modifier = Modifier.height(16.dp))

                Image(
                    painter = painterResource(R.drawable.game_rule_dead_cell_alive_rule),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(200.dp)
                )
                Text(
                    text = stringResource(R.string.game_rule_dialog_explain_3),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = stringResource(R.string.game_rule_dialog_explain_4),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    )
}