package org.isaaccode.calculategame.components

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.isaaccode.calculategame.SettingsScreen
import org.isaaccode.calculategame.data.model.Settings
import org.isaaccode.calculategame.data.repository.SettingsRepository
import org.isaaccode.calculategame.getPersistenceAccessor

@Composable
fun AppNavigation(taskViewModel: TaskViewModel, navController: NavHostController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route ?: ""

    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier
            .fillMaxSize()
    ) {
        composable("home") {
            val repo = SettingsRepository(getPersistenceAccessor())


            MainComponent(taskViewModel, navController)
        }

        composable("settings") {

            val repo = SettingsRepository(getPersistenceAccessor())
            val currentSettings: Settings = repo.state.value

            SettingsScreen(currentSettings, { settings -> repo.saveSettings(settings)
            navController.navigate("home")})
        }

        composable("result") {

            ResultComponent(taskViewModel, navController)
        }
    }
}