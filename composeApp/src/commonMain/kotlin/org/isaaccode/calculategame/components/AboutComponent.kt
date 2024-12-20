package org.isaaccode.calculategame.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.isaaccode.calculategame.resources.Theme.Companion.currentTheme

@Composable
fun AboutComponent(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        TopAppBar(
            title = { Text("Info") },
            backgroundColor = currentTheme.colors.accentColor,
            navigationIcon = { IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Title: App Name
            Text(
                text = "Racunaljka", // Replace with your app name
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.primary
            )

            // App Version
            Text(
                text = "Verzija 1.0.0",
                style = TextStyle(fontSize = 18.sp),
                color = Color.Gray
            )

            // Description
            Text(
                text = "Aplikacija Racunaljka namjenjena za djecu ili odrasle koji zele da poboljsaju svoje matematicke sposobnosti kroz igru.",
                style = TextStyle(fontSize = 20.sp),
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            // Developer info or Social Media links
            Text(
                text = "Developed by Isaac Code",
                style = TextStyle(fontSize = 16.sp),
                color = Color.Gray
            )
    }
    }
}