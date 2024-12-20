package org.isaaccode.calculategame.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.isaaccode.calculategame.resources.Theme

@Composable
fun MainMenuComponent(navController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MainMenuButton("Play", { navController.navigate(NavigationItem.Home.route)})
        MainMenuButton("Settings", { navController.navigate(NavigationItem.Settings.route)})
        MainMenuButton("About",{ navController.navigate(NavigationItem.About.route)})
    }
}

@Composable
fun MainMenuButton(text: String, menuButtonOnClick: () -> Unit) {
    Button(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        onClick = { menuButtonOnClick() }
    ) {
        Text(
            fontFamily = FontFamily.Serif,
            fontSize = Theme.FontSizes.extraLarge,
            text = text)
    }
}