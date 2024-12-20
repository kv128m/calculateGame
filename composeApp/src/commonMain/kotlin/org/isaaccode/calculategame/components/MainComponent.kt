package org.isaaccode.calculategame.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import org.isaaccode.calculategame.resources.Theme.Companion.currentTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun MainComponent(viewModel: TaskViewModel, navController: NavHostController) {

    Column(
        modifier = Modifier
            .background(currentTheme.colors.primaryBackgroundColor)
    ) {
        TaskComponent(viewModel, navController)
        ScoresComponent(viewModel)
    }

}