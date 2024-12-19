package org.isaaccode.calculategame

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import moe.tlaster.precompose.PreComposeApp
import org.isaaccode.calculategame.components.AppNavigation
import org.isaaccode.calculategame.components.LocalizedStrings
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.isaaccode.calculategame.components.MainComponent
import org.isaaccode.calculategame.components.TaskViewModel
import org.isaaccode.calculategame.data.repository.SettingsRepository

@Composable
@Preview
fun App() {
    PreComposeApp {
        MaterialTheme {
            val repo = SettingsRepository(getPersistenceAccessor())

            val viewModel: TaskViewModel =  TaskViewModel(repo)

            //MainComponent(viewModel)

            val navController: NavHostController = rememberNavController()

            AppNavigation(viewModel, navController)
        }
    }
}