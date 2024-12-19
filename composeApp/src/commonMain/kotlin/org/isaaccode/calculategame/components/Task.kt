package org.isaaccode.calculategame.components

enum class Operand(val operand: String) {
    PLUS("+"),
    MINUS("-"),
    MUL("*"),
    DIV("/")
}

data class Task(
    val first: Int,
    val operand: Operand,
    val second: Int,
    val answers: List<Int>,
    val correctAnswer: Int,
    val timestampShown: Long,
) {

}