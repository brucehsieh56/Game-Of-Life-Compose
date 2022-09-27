package app.bruce.gameoflifecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.rememberNavController
import app.bruce.gameoflifecompose.common.routing.SetUpNavigation
import app.bruce.gameoflifecompose.ui.theme.GameOfLifeComposeTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameOfLifeComposeTheme {
                window.statusBarColor = MaterialTheme.colorScheme.surface.toArgb()
                val navController = rememberNavController()
                SetUpNavigation(navHostController = navController)
            }
        }
    }
}