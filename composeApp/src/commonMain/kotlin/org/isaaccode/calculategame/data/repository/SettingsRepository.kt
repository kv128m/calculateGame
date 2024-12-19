package org.isaaccode.calculategame.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import org.isaaccode.calculategame.components.Operand
import org.isaaccode.calculategame.data.model.Difficulty
import org.isaaccode.calculategame.data.model.Difficulty.Companion.toDifficulty
import org.isaaccode.calculategame.data.model.Difficulty.Companion.toLong
import org.isaaccode.calculategame.data.model.Settings
import org.isaaccode.calculategame.persistence.PersistenceAccessor

private const val DIFFICULTY = "difficulty"
private const val OPERANDS = "operands"
private const val UPPER_LIMITS = "upperLimits"
private const val NUMBER_OF_TASKS = "numberOfTasks"

class SettingsRepository(private val accessor: PersistenceAccessor) {


    private val _state: MutableStateFlow<Settings> = MutableStateFlow(readState())

    val state: StateFlow<Settings> = _state

    fun saveSettings(settings: Settings) {

        accessor.set(DIFFICULTY, settings.taskDifficulty.toLong())
        accessor.set(OPERANDS, settings.operands.encode())
        accessor.set(UPPER_LIMITS, settings.upperLimits.toLong())
        accessor.set(NUMBER_OF_TASKS, settings.numberOfTasks)

        _state.value = settings
    }

    private fun readState(): Settings {
        val difficulty = accessor.get(DIFFICULTY).toDifficulty()
        val operands = accessor.get(OPERANDS)
        val upperLimits = accessor.get(UPPER_LIMITS)
        val numberOfTasks = accessor.get(NUMBER_OF_TASKS)

        val settings: Settings = createSettings(difficulty, operands, upperLimits, numberOfTasks)

        return settings
    }

    private fun createSettings(difficulty: Difficulty, operands: Long, upperLimits: Long, numberOfTasks: Long): Settings {
        val operandList: MutableList<Operand> = mutableListOf()
        if (operands % 2 == 0L) {
            operandList += Operand.PLUS
        }
        if (operands % 3 == 0L) {
            operandList += Operand.MINUS
        }
        if (operands % 5 == 0L) {
            operandList += Operand.MUL
        }
        if (operands % 7 == 0L) {
            operandList += Operand.DIV
        }

        val limitList = mutableListOf<Long>()
        if (upperLimits == 0L) {
            limitList.addAll(listOf(10, 10, 20, 20))
        } else {
            val limitDivPlus = upperLimits % 1000
            val limitDivMinus = (upperLimits / 1000) % 1000
            val limitDivMul = (upperLimits / 1000000) % 1000
            val limitDivDiv = (upperLimits / 1000000000)
            limitList.addAll(listOf(limitDivDiv, limitDivMul, limitDivMinus, limitDivPlus))
        }

        val nrOfTasks = if (numberOfTasks <= 0L) 20 else numberOfTasks

        return Settings(
            taskDifficulty = difficulty,
            operands = operandList,
            upperLimits = limitList,
            numberOfTasks = nrOfTasks
        )
    }

    private fun List<Operand>.encode(): Long {
        var result = 1L
        if (this.any { it == Operand.PLUS}) result *= 2
        if (this.any { it == Operand.MINUS}) result *= 3
        if (this.any { it == Operand.MUL}) result *= 5
        if (this.any { it == Operand.DIV}) result *= 7

        return result
    }

    private fun List<Long>.toLong() = this.fold(0L) { acc, elem -> acc * 1000 + elem }


}