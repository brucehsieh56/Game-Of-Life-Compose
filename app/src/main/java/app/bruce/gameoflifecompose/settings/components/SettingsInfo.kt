package app.bruce.gameoflifecompose.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileCopy
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.bruce.gameoflifecompose.BuildConfig
import app.bruce.gameoflifecompose.R
import app.bruce.gameoflifecompose.common.utils.IntentHelper

@Composable
fun SettingsInfo() {
    val context = LocalContext.current

    fun onGoToTermsAndConditionsLaunch() {
        context.startActivity(IntentHelper.getGoToTermsAndConditionsIntent())
    }

    fun onGoToPrivacyPolicyLaunch() {
        context.startActivity(IntentHelper.getGoToPrivacyPolicyIntent())
    }

    Row(
        modifier = Modifier
            .height(60.dp)
            .clickable(onClick = ::onGoToTermsAndConditionsLaunch),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.FileCopy,
            contentDescription = stringResource(R.string.settings_screen_terms_and_conditions),
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = stringResource(R.string.settings_screen_terms_and_conditions),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.titleMedium
        )
    }
    Row(
        modifier = Modifier
            .height(60.dp)
            .clickable(onClick = ::onGoToPrivacyPolicyLaunch),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.FileCopy,
            contentDescription = stringResource(R.string.settings_screen_privacy_policy),
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = stringResource(R.string.settings_screen_privacy_policy),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.titleMedium
        )
    }
    Row(
        modifier = Modifier
            .height(60.dp)
            .clickable { },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.Info,
            contentDescription = stringResource(R.string.settings_screen_app_version),
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = "${stringResource(R.string.settings_screen_app_version)}: ${BuildConfig.VERSION_NAME}",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.titleMedium
        )
    }
}