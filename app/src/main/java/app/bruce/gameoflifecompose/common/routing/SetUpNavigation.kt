package app.bruce.gameoflifecompose.common.routing

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.bruce.gameoflifecompose.game.GameScreen
import app.bruce.gameoflifecompose.settings.SettingsScreen

/**
 * Set up navigation graph.
 * */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetUpNavigation(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Router.GameScreen.route
    ) {
        composable(route = Router.GameScreen.route) {
            GameScreen(navHostController = navHostController)
        }
        composable(route = Router.SettingsScreen.route) {
            SettingsScreen(navHostController = navHostController)
        }
    }
}