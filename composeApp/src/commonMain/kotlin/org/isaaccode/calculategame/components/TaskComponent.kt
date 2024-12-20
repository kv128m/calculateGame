package org.isaaccode.calculategame.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import org.isaaccode.calculategame.getDateTimeProvider
import org.isaaccode.calculategame.resources.Theme.Companion.currentTheme

enum class ButtonState { Pressed, Idle }
fun Modifier.bounceClick() = composed {
    var buttonState by remember { mutableStateOf(ButtonState.Idle) }
    val scale by animateFloatAsState(if (buttonState == ButtonState.Pressed) 0.70f else 1f)

    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = {  }
        )
        .pointerInput(buttonState) {
            awaitPointerEventScope {
                buttonState = if (buttonState == ButtonState.Pressed) {
                    waitForUpOrCancellation()
                    ButtonState.Idle
                } else {
                    awaitFirstDown(false)
                    ButtonState.Pressed
                }
            }
        }
}

@Composable
fun TaskComponent(taskViewModel: TaskViewModel, navController: NavHostController) {

    val task by taskViewModel.taskState.collectAsState()

    fun Task.forPrint() = "$first  ${operand.operand}  $second  =  ?"

    val taskDurationInSeconds = taskViewModel.getDifficulty().durationNoPoints
    var progress by remember { mutableStateOf(0f) }

    LaunchedEffect(task) {
        val startTime = getDateTimeProvider().now()
        val taskEndTime = startTime + taskDurationInSeconds * 1000L
        while (getDateTimeProvider().now() < taskEndTime) {
            val elapsedTime = getDateTimeProvider().now() - startTime
            progress = elapsedTime.toFloat() / (taskDurationInSeconds * 1000)
            delay(10)
        }
        progress = 1f
    }

    TopAppBar(
        title = { Text("Zadatak") },
        backgroundColor = currentTheme.colors.accentColor,
        navigationIcon = {
            IconButton( onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            IconButton( onClick = { taskViewModel.newGame() }) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        }
    )

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(currentTheme.colors.primaryBackgroundColor)
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Box(
                modifier = Modifier
                    .alpha(0f)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onLongPress = { navController.navigate(NavigationItem.Settings.route)}
                        )
                    }
                    .width(30.dp)
                    .height(30.dp)
                    .background(currentTheme.colors.primaryBackgroundColor)
            ) {}
            Text(
                text = task.forPrint(),
                fontSize = currentTheme.fontSizes.title,
                fontFamily = FontFamily.Serif,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                textAlign = TextAlign.Center
            )
        }

        Row(
            modifier = Modifier.padding(vertical = 20.dp)
        ) {
            task.answers.forEach { answer ->
                Button(
                    modifier = Modifier
                        .padding(vertical = 20.dp, horizontal = 12.dp)
                        .background(Color(143, 206, 0))
                        .bounceClick(),
                    onClick = {
                        val resultsLimit = taskViewModel.answerSelected(task, answer)
                        if (resultsLimit.any()) {
                            navController.navigate(NavigationItem.Results.route)
                        }
                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(Color.DarkGray)
                ) {
                    Text(
                        text = answer.toString(),
                        color = Color.White,
                        fontSize = currentTheme.fontSizes.tableCell,
                        fontFamily = FontFamily.Serif,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(currentTheme.paddingSizes.tableCell)
                .height(10.dp),
            progress = (1.0f - progress),
            color = if (progress > 0.5f) currentTheme.colors.warningColor else currentTheme.colors.accentColor
        )
    }
}