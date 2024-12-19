package org.isaaccode.calculategame.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import org.isaaccode.calculategame.getResourceLoader
import org.isaaccode.calculategame.resources.ResourceLoader
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun MainComponent(viewModel: TaskViewModel, navController: NavHostController) {

    Column(

    ) {
        TaskComponent(viewModel, navController)
        ScoresComponent(viewModel, navController)
    }

}