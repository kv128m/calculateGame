package org.isaaccode.calculategame.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import org.isaaccode.calculategame.SettingsComponent
import org.isaaccode.calculategame.data.repository.SettingsRepository
import org.isaaccode.calculategame.getPersistenceAccessor

enum class Routes {
    MainMenu,
    Home,
    Settings,
    Results,
    About
}

sealed class NavigationItem(val route: String) {
    object MainMenu: NavigationItem(Routes.MainMenu.name)
    object Home: NavigationItem(Routes.Home.name)
    object Settings: NavigationItem(Routes.Settings.name)
    object Results: NavigationItem(Routes.Results.name)
    object About: NavigationItem(Routes.About.name)
}

@Composable
fun AppNavigation(taskViewModel: TaskViewModel, navController: NavHostController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route ?: ""

    NavHost(
        navController = navController,
        startDestination = NavigationItem.MainMenu.route,
        modifier = Modifier
            .fillMaxSize()
    ) {
        composable(NavigationItem.MainMenu.route) {
            MainMenuComponent(navController)
        }

        composable(NavigationItem.Home.route) {
            taskViewModel.refreshRepository(SettingsRepository(getPersistenceAccessor()))
            MainComponent(taskViewModel, navController)
        }

        composable(NavigationItem.Settings.route) {
            SettingsComponent(navController)
        }

        composable(NavigationItem.Results.route) {
            ResultComponent(taskViewModel, navController)
        }

        composable(NavigationItem.About.route) {
            AboutComponent(navController)
        }
    }
}