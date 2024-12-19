package org.isaaccode.calculategame.components

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

data class Score(
    val id: Int,
    val task: String,
    val result: Int,
    val points: Int
)
