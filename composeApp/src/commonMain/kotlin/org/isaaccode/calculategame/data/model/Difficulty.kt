package org.isaaccode.calculategame.data.model

sealed class Difficulty(
    val durationFullPoints: Long,
    val durationMinPoints: Long,
    val durationMediumPoints: Long,
    val durationNoPoints: Long,
    val pointsFull: Long,
    val pointsMin: Long,
    val pointsMedium: Long,
    val negativePoints: Long,
    val name: String
) {
    data object Hard: Difficulty(
        durationFullPoints = 1,
        durationMinPoints = 5,
        durationMediumPoints = 3,
        durationNoPoints = 10,
        pointsFull = 10,
        pointsMin = 3,
        pointsMedium = 5,
        negativePoints = -3,
        name = "Hard"
    )
    data object Medium: Difficulty(
        durationFullPoints = 3,
        durationMinPoints = 10,
        durationMediumPoints = 5,
        durationNoPoints = 15,
        pointsFull = 7,
        pointsMin = 2,
        pointsMedium = 4,
        negativePoints = -2,
        name = "Medium"
    )
    data object Easy: Difficulty(
        durationFullPoints = 5,
        durationMinPoints = 15,
        durationMediumPoints = 8,
        durationNoPoints = 25,
        pointsFull = 5,
        pointsMin = 1,
        pointsMedium = 2,
        negativePoints = -1,
        name = "Easy"
    )

    companion object {

        fun Long.toDifficulty() = when (this) {
            1L -> Hard
            2L -> Medium
            else -> Easy
        }

        fun Difficulty.toLong() = when(this) {
            Easy -> 3L
            Medium -> 2L
            Hard -> 1L
        }
    }
}