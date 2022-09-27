package app.bruce.gameoflifecompose.common.utils

import android.content.Intent
import android.net.Uri
import app.bruce.gameoflifecompose.BuildConfig

/**
 * Create an email [Intent].
 * */
object IntentHelper {

    fun getEmailIntent(): Intent {
        val email = "bruce.app.dev@gmail.com"
        val subject = "[App Feedback]"
        val body = "Your feedback here."
        val urlString =
            "mailto:${Uri.encode(email)}?subject=${Uri.encode(subject)}&body=${Uri.encode(body)}"

        val selectorIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse(urlString)
        }

        val emailIntent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, body)
            selector = selectorIntent
        }

        return emailIntent
    }

    fun getShareAppIntent(appName: String): Intent {
        val shareMessage =
            """
                Hey! I think you'll enjoy Game Of Life - Compose, a free zero-player game.
                
                https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}
            """.trimIndent()

        return Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, appName)
            putExtra(Intent.EXTRA_TEXT, shareMessage)
        }
    }

    fun getGoToAppStoreIntent(): Intent {
        return Intent(
            Intent.ACTION_VIEW,
            Uri.parse("market://details?id=${BuildConfig.APPLICATION_ID}")
        )
    }

    fun getGoToTermsAndConditionsIntent(): Intent {
        return Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://github.com/tRexBruce/PrivayPolicy/blob/main/TermsAndConditions.md")
        )
    }

    fun getGoToPrivacyPolicyIntent(): Intent {
        return Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://github.com/tRexBruce/PrivayPolicy/blob/main/PrivacyPolicy.md")
        )
    }
}