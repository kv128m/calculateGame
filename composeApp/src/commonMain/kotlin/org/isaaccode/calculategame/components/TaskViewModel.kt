package org.isaaccode.calculategame.components

import kotlinx.coroutines.flow.MutableStateFlow
import moe.tlaster.precompose.viewmodel.ViewModel
import org.isaaccode.calculategame.data.model.Difficulty
import org.isaaccode.calculategame.data.repository.SettingsRepository
import org.isaaccode.calculategame.getDateTimeProvider
import org.isaaccode.calculategame.getPersistenceAccessor

class TaskViewModel(private var repo: SettingsRepository) : ViewModel() {

    val taskState = MutableStateFlow(generateTask())

    val resultsState = MutableStateFlow(emptyList<Score>())

    fun getDifficulty(): Difficulty {
        val difficulty = repo.state.value.taskDifficulty

        return difficulty
    }

    fun refreshRepository(settingsRepository: SettingsRepository) {
        repo = settingsRepository
    }

    private fun generateFirst(operand: Operand): Int {

        val upperLimits = repo.state.value.upperLimits

       return when(operand) {
           Operand.PLUS -> (1..upperLimits[3].toInt()).random()
           Operand.MINUS -> (1..upperLimits[2].toInt()).random()
           Operand.MUL -> (1..upperLimits[1].toInt()).random()
           Operand.DIV -> generateDiv(upperLimits[0].toInt())
       }
    }

    private fun generateDiv(upperLimit: Int): Int {
        val rand = (1..upperLimit).random()
        val rand2 = (1..upperLimit).random()

        return rand * rand2
    }

    private fun generateSecond(first: Int, operand: Operand): Int {

        val upperLimits = repo.state.value.upperLimits

        return when(operand) {
            Operand.PLUS -> (0..(upperLimits[3].toInt() - first)).random()
            Operand.MINUS -> (0..first).random()
            Operand.MUL -> (1..upperLimits[1].toInt()).random()
            Operand.DIV -> division(first)
        }
    }

    private fun division(first: Int): Int {

        val upperLimit = repo.state.value.upperLimits[0].toInt()
        while (true) {
            val second = (1..upperLimit).random()
            if (first % second == 0) {
                val result = first / second
                if (result <= upperLimit) {
                    return second
                }
            }
        }
    }

    fun answerSelected(t: Task, selected: Int): List<Score> {
        save(t, selected)
        taskState.value = generateTask()

        return if (resultsState.value.size >= repo.state.value.numberOfTasks) {
            resultsState.value
        } else {
            emptyList()
        }
    }

    private fun Task.forPrint() = "$first ${operand.operand} $second"

    private fun save(t: Task, selected: Int) {
        val previousState = resultsState.value

        val deltaTime = getDateTimeProvider().now() -  t.timestampShown

        val difficulty = repo.state.value.taskDifficulty

        val points = when {
            t.correctAnswer != selected -> difficulty.negativePoints
            deltaTime < difficulty.durationFullPoints * 1000 -> difficulty.pointsFull
            deltaTime < difficulty.durationMediumPoints * 1000 -> difficulty.pointsMedium
            deltaTime < difficulty.durationMinPoints * 1000 -> difficulty.pointsMin
            else -> 0
        }

        val newScore = Score(
                previousState.size + 1,
                task = t.forPrint(),
                result = selected,
                points = points.toInt(),
            )

        resultsState.value = previousState + newScore
    }

    private fun generateTask(): Task {

        val operand = getRandomOperand()
        val first = generateFirst(operand)
        val second = generateSecond(first, operand)
        val answers = generateAnswers(first, second, operand)
        val correctAnswer = getCorrectAnswer(first, second, operand)
        val timestampShown = getDateTimeProvider().now()

        val task = Task(
            first = first,
            operand = operand,
            second = second,
            answers = answers,
            correctAnswer = correctAnswer,
            timestampShown = timestampShown
        )

        return task
    }

    private fun getRandomOperand(): Operand {
        return repo.state.value.operands.random()
    }

    private fun getCorrectAnswer(first: Int, second: Int, operand: Operand): Int {
         return calculateResult(first, second, operand)
    }

    private fun generateAnswers(first: Int, second: Int, operand: Operand): List<Int> {

        val answer1 = calculateResult(first, second, operand)
        val answer2 = wrongAnswer(listOf(answer1), operand)
        val answer3 = wrongAnswer(listOf(answer1, answer2), operand)
        return listOf(answer1, answer2, answer3).shuffled()
    }

    private fun wrongAnswer(answers: List<Int>, operand: Operand): Int {
        val upperLimits = repo.state.value.upperLimits

        while (true) {
            val limit = when(operand) {
                Operand.PLUS -> upperLimits[3]
                Operand.MINUS -> upperLimits[2]
                Operand.MUL -> upperLimits[1] * upperLimits[1]
                Operand.DIV -> upperLimits[0]
            }.toInt()
            val rand = (1..limit).random()
            if (rand !in answers) {
                return rand
            }
        }
    }

    private fun calculateResult(first: Int, second: Int, operand: Operand): Int {
        return when (operand) {
            Operand.PLUS -> first + second
            Operand.MINUS -> first - second
            Operand.MUL -> first * second
            Operand.DIV -> first / second
        }
    }

    fun newGame() {
        resultsState.value = emptyList()
    }
}
