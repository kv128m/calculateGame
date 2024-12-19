package org.isaaccode.calculategame.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import org.isaaccode.calculategame.getDateTimeProvider
import org.isaaccode.calculategame.resources.Theme
import org.isaaccode.calculategame.resources.Theme.Companion.currentTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.measureTime

@Preview
@Composable
fun showProgress(score : Int =100){


    val gradient = Brush.linearGradient(listOf(Color(0xFFF95075),
        Color(0xFFBE6BE5)))


    val progressFactor by remember(score) {
        mutableStateOf(score*0.005f)
    }

    Row(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth().height(45.dp).border(
            width = 4.dp,
            brush = Brush.linearGradient(
                colors = listOf(
                    currentTheme.colors.warningColor,
                    currentTheme.colors.warningColor
                )
            ) ,
            shape = RoundedCornerShape(50.dp)
        )
        .clip(
            RoundedCornerShape(
                topStartPercent = 50,
                topEndPercent = 50,
                bottomEndPercent = 50,
                bottomStartPercent = 50
            )
        )
        .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Button(
            contentPadding = PaddingValues(1.dp),
            onClick = { },
            modifier = Modifier
                .fillMaxWidth(progressFactor)
                .background(brush = gradient),
            enabled = false,
            elevation = null,
            colors = buttonColors(
                backgroundColor = Color.Transparent,
                disabledBackgroundColor = Color.Transparent
            )) {

            Text(text = (score * 10).toString(),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(23.dp))
                    .fillMaxHeight(0.87f)
                    .fillMaxWidth()
                    .padding(7.dp),
                color= currentTheme.colors.inactiveColor,
                textAlign = TextAlign.Center)
        }
    }
}

@Composable
@Preview
fun ScoresComponent(viewModel: TaskViewModel, navController: NavHostController) {

        val lazyColumnListState = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()
        val scoreList by viewModel.resultsState.collectAsState()

        /*

        val totalDuration = 10000L

        var startTime by remember { mutableStateOf(getDateTimeProvider().now()) }
        var progress by remember { mutableStateOf(0f) }

        // LaunchedEffect to track time and update progress
        LaunchedEffect(key1 = true) {
            while (progress < 1f) {
                val elapsedTime = getDateTimeProvider().now() - startTime  // Time elapsed since start
                progress = (elapsedTime.toFloat() / totalDuration).coerceIn(0f, 1f)  // Calculate progress as percentage
                //delay(100)  // Update progress every 100 milliseconds
            }
        }

        // Animate the progress bar smoothly (use a float value from 0 to 1)
        val animatedProgress = animateFloatAsState(
            targetValue = progress,
            animationSpec = tween(durationMillis = 500) // Smooth animation to target value
        )

         */

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(currentTheme.colors.primaryBackgroundColor)
        ) {
            /*
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(currentTheme.paddingSizes.tableCell)
                    .height(10.dp),
                progress = 0.5f,
                backgroundColor = currentTheme.colors.inactiveColor,
                color = currentTheme.colors.warningColor
            )
            */

            LaunchedEffect(scoreList.size) {
                lazyColumnListState.animateScrollToItem(lazyColumnListState.layoutInfo.totalItemsCount)
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(currentTheme.colors.primaryBackgroundColor),
                state = lazyColumnListState
            ) {
                coroutineScope.launch {
                    if (scoreList.size == 10) {
                        lazyColumnListState.scrollToItem(scoreList.size - 1)
                    }
                }

                items(scoreList.size) { index ->
                    ScoreComponent(ScoreComponentOptions.Regular, scoreList[index])
                }
            }
        }
}
