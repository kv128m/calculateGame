package org.isaaccode.calculategame.data.model

import org.isaaccode.calculategame.components.Operand

data class Settings(
    val taskDifficulty: Difficulty,
    val operands: List<Operand>,
    val upperLimits: List<Long>,
    val numberOfTasks: Long
)
