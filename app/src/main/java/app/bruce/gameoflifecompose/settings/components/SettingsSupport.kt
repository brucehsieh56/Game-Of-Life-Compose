package app.bruce.gameoflifecompose.settings.components

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.bruce.gameoflifecompose.R
import app.bruce.gameoflifecompose.common.utils.IntentHelper

@Composable
fun SettingsSupport() {
    val context = LocalContext.current

    fun onEmailSentLaunched() {
        val chooserTitle = "Draft email"
        val emailIntent = IntentHelper.getEmailIntent()
        context.startActivity(Intent.createChooser(emailIntent, chooserTitle))
    }

    fun onAppShareLaunched() {
        val chooserTitle = "Share app"
        val appShareIntent = IntentHelper.getShareAppIntent(
            context.getString(R.string.app_name)
        )
        context.startActivity(Intent.createChooser(appShareIntent, chooserTitle))
    }

    fun onAppRateLaunch() {
        context.startActivity(IntentHelper.getGoToAppStoreIntent())
    }

    Row(
        modifier = Modifier
            .height(60.dp)
            .clickable(onClick = ::onEmailSentLaunched),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.Email,
            contentDescription = stringResource(R.string.settings_screen_send_feedback),
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = stringResource(R.string.settings_screen_send_feedback),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.titleMedium
        )
    }
    Row(
        modifier = Modifier
            .height(60.dp)
            .clickable(onClick = ::onAppShareLaunched),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.Share,
            contentDescription = stringResource(R.string.settings_screen_share_this_app),
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = stringResource(R.string.settings_screen_share_this_app),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.titleMedium
        )
    }
    Row(
        modifier = Modifier
            .height(60.dp)
            .clickable(onClick = ::onAppRateLaunch),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.ThumbUp,
            contentDescription = stringResource(R.string.settings_screen_rate_this_app),
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = stringResource(R.string.settings_screen_rate_this_app),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.titleMedium
        )
    }
}