package com.example.scheduler

import java.util.ArrayList

fun Generator(subjects: List<String>): Array<Array<List<String>?>> {

    // Define the number of lectures per day and week
    val numLecturesPerDay = 8
    val numDaysPerWeek = 6

    // Create a list of all possible lecture combinations
    val possibleLectureCombinations: MutableList<List<String>> =
        ArrayList(subjects).repeat(numLecturesPerDay).toList().shuffled() as MutableList<List<String>>

    // Create an empty schedule
    val schedule = Array(numDaysPerWeek) { arrayOfNulls<List<String>>(numLecturesPerDay) }

    // Iterate through each day of the week
    for (i in 0 until numDaysPerWeek) {
        // Iterate through each lecture of the day
        for (j in 0 until numLecturesPerDay) {
            // Get the next lecture from the shuffled list of possible lecture combinations
            val nextLecture = possibleLectureCombinations.removeFirst()

            // Add the lecture to the schedule for the current day
            schedule[i][j] = nextLecture
        }
    }

    // Print the schedule
    for (i in 0 until numDaysPerWeek) {
        println("Day ${i + 1}:")
        for (j in 0 until numLecturesPerDay) {
            println("Lecture ${j + 1}: ${schedule[i][j]}")
        }
        println()
    }
    return schedule
}

fun <T> List<T>.repeat(n: Int): List<List<T>> {
    return this.flatMap { value -> List(n) { listOf(value) } }
}
