package com.example.individualquiz

import kotlin.random.Random

object Ordering {

    fun getQuestions(): Pair<String, Boolean> {
        val num1 = Random.nextInt(1, 101)
        val num2 = Random.nextInt(1, 101)
        val num3 = Random.nextInt(1, 101)
        val num4 = Random.nextInt(1, 101)

        val numbers = listOf(num1, num2, num3, num4).sorted()
        val problem = "$numbers[0], $numbers[1], $numbers[2], $numbers[3]"

        return Pair(problem, numbers[1] < numbers[2])
    }
}