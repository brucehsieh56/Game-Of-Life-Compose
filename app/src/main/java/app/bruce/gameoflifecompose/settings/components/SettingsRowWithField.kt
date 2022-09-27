package app.bruce.gameoflifecompose.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.bruce.gameoflifecompose.common.domain.GamePreferences

@ExperimentalMaterial3Api
@Composable
fun <T : Enum<T>> SettingsRowWithField(
    gamePreferences: GamePreferences<T>,
    textTitle: Int,
    imageVector: ImageVector,
    onClick: (String) -> Unit,
) {
    Row(
        modifier = Modifier.height(60.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        var expanded by remember { mutableStateOf(false) }
        Icon(
            imageVector = imageVector,
            contentDescription = stringResource(textTitle),
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = stringResource(textTitle),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.titleMedium
        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.weight(1f)
        ) {
            OutlinedTextField(
                readOnly = true,
                value = stringResource(id = gamePreferences.mapToText()),
                onValueChange = {},
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                singleLine = true,
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                content = {
                    gamePreferences.getAllValues().forEach { selectionOption ->
                        DropdownMenuItem(
                            text = {
                                Text(stringResource(id = selectionOption.mapToText()))
                            },
                            onClick = {
                                onClick((selectionOption as T).name)
                                expanded = false
                            }
                        )
                    }
                }
            )
        }
    }
}