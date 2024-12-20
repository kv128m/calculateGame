package org.isaaccode.calculategame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.isaaccode.calculategame.components.Operand
import org.isaaccode.calculategame.data.model.Difficulty
import org.isaaccode.calculategame.data.model.Settings
import org.isaaccode.calculategame.data.repository.SettingsRepository
import org.isaaccode.calculategame.resources.Theme.Companion.currentTheme

@Composable
fun SettingsComponent(navController: NavHostController) {

    val repo = SettingsRepository(getPersistenceAccessor())
    val currentSettings: Settings = repo.state.value

    SettingsScreen(currentSettings, {
        settings -> repo.saveSettings(settings)
        navController.popBackStack()
    }, Modifier, navController)
}

@Composable
fun SettingsScreen(
    settings: Settings,
    onSettingsChange: (Settings) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    var taskDifficulty by remember { mutableStateOf(settings.taskDifficulty) }
    var selectedOperands by remember { mutableStateOf(settings.operands.toSet()) }
    var upperLimits by remember { mutableStateOf(settings.upperLimits.reversed()) }
    var numberOfTasks by remember { mutableStateOf(settings.numberOfTasks) }

    SettingsUI(
        taskDifficulty = taskDifficulty,
        onTaskDifficultyChange = { taskDifficulty = it },
        selectedOperands = selectedOperands,
        onOperandsChange = { selectedOperands = it },
        upperLimits = upperLimits,
        onUpperLimitsChange = { upperLimits = it },
        numberOfTasks = numberOfTasks,
        onNumberOfTasksChange = { numberOfTasks = it },
        onSave = {
            onSettingsChange(Settings(taskDifficulty, selectedOperands.toList(), upperLimits.reversed(), numberOfTasks))
        },
        modifier = modifier,
        navController
    )
}

@Composable
fun SettingsUI(
    taskDifficulty: Difficulty,
    onTaskDifficultyChange: (Difficulty) -> Unit,
    selectedOperands: Set<Operand>,
    onOperandsChange: (Set<Operand>) -> Unit,
    upperLimits: List<Long>,
    onUpperLimitsChange: (List<Long>) -> Unit,
    numberOfTasks: Long,
    onNumberOfTasksChange: (Long) -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        TopAppBar(
            title = { Text("Postavke") },
            backgroundColor = currentTheme.colors.accentColor,
            navigationIcon = {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                }
            }
        )

        Column(modifier = modifier.padding(16.dp)) {

            // Task Difficulty
            Text("Tezina zadatka", style = MaterialTheme.typography.h6)
            DifficultySelector(taskDifficulty, onTaskDifficultyChange)

            Spacer(modifier = Modifier.height(16.dp))

            // Operand Selector
            Text("Operandi", style = MaterialTheme.typography.h6)
            OperandSelector(selectedOperands, onOperandsChange)

            Spacer(modifier = Modifier.height(16.dp))

            // Upper Limits Selector
            Text("Limit brojeva", style = MaterialTheme.typography.h6)
            UpperLimitsSelector(upperLimits, onUpperLimitsChange)

            Spacer(modifier = Modifier.height(16.dp))

            // Number of Tasks Selector
            Text("Broj zadataka", style = MaterialTheme.typography.h6)
            NumberOfTasksSelector(numberOfTasks, onNumberOfTasksChange)

            Spacer(modifier = Modifier.height(24.dp))

            // Save Button
            Button(onClick = onSave, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text("Sacuvaj")
            }
        }
    }
}

@Composable
fun DifficultySelector(selectedDifficulty: Difficulty, onDifficultyChange: (Difficulty) -> Unit) {

    val diffList = listOf(Difficulty.Easy, Difficulty.Medium, Difficulty.Hard)

    Row {
        diffList.forEach { difficulty ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedDifficulty == difficulty,
                    onClick = { onDifficultyChange(difficulty) }
                )
                Text(difficulty.name)
            }
        }
    }
}

@Composable
fun OperandSelector(selectedOperands: Set<Operand>, onOperandsChange: (Set<Operand>) -> Unit) {
    Row {
        Operand.values().forEach { operand ->

            val operandName = when(operand) {
                Operand.PLUS -> "+"
                Operand.MINUS -> "-"
                Operand.MUL -> "*"
                Operand.DIV -> "/"
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = selectedOperands.contains(operand),
                    onCheckedChange = { isChecked ->
                        val updatedOperands = if (isChecked) {
                            selectedOperands + operand
                        } else {
                            selectedOperands - operand
                        }
                        onOperandsChange(updatedOperands)
                    }
                )
                Text(operandName)
            }
        }
    }
}

@Composable
fun UpperLimitsSelector(upperLimits: List<Long>, onUpperLimitsChange: (List<Long>) -> Unit) {
    upperLimits.forEachIndexed { index, limit ->
        SliderWithLabel(index, limit, onValueChange = { newValue ->
            val updatedLimits = upperLimits.toMutableList()
            updatedLimits[index] = newValue.toLong()
            onUpperLimitsChange(updatedLimits)
        })
    }
}

@Composable
fun SliderWithLabel(index: Int, limit: Long, onValueChange: (Float) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        val operandName = when(index) {
            0 -> "+"
            1 -> "-"
            2 -> "*"
            3 -> "/"
            else -> { "+" }
        }
        Text("Limit za operand $operandName", style = MaterialTheme.typography.body1)
        Slider(
            value = limit.toFloat(),
            onValueChange = onValueChange,
            valueRange = 0f..100f,
            modifier = Modifier.fillMaxWidth()
        )
        Text("Vrijednost: $limit", style = MaterialTheme.typography.body2)
    }
}

@Composable
fun NumberOfTasksSelector(numberOfTasks: Long, onNumberOfTasksChange: (Long) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Broj zadataka", style = MaterialTheme.typography.body1)
        Slider(
            value = numberOfTasks.toFloat(),
            onValueChange = { onNumberOfTasksChange(it.toLong()) },
            valueRange = 1f..20f,
            steps = 19,
            modifier = Modifier.fillMaxWidth()
        )
        Text("Vrijednost: $numberOfTasks", style = MaterialTheme.typography.body2)
    }
}

