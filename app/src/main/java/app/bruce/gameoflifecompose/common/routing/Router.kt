package app.bruce.gameoflifecompose.common.routing

/**
 * A sealed class that includes all the navigation destinations.
 * */
sealed class Router(val route: String) {
    object GameScreen : Router(route = ROUTE_GAME_SCREEN)
    object SettingsScreen : Router(route = ROUTE_SETTINGS_SCREEN)

    companion object {
        const val ROUTE_GAME_SCREEN = "ROUTE_GAME_SCREEN"
        const val ROUTE_SETTINGS_SCREEN = "ROUTE_SETTINGS_SCREEN"
    }
}