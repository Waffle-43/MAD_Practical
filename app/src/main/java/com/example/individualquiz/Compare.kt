package com.example.individualquiz

import kotlin.random.Random

interface ComparisonOperator {
    fun compare(num1: Int, num2: Int): Boolean
    val operation: String
}

enum class GreaterThan : ComparisonOperator {
    GREATER_THAN {
        override fun compare(num1: Int, num2: Int): Boolean {
            return num1 > num2
        }
        override val operation: String = ">"
    }
}

enum class LessThan : ComparisonOperator {
    LESS_THAN {
        override fun compare(num1: Int, num2: Int): Boolean {
            return num1 < num2
        }
        override val operation: String = "<"
    }
}

data class QuestionCompare(val num1: Int, val num2: Int, val operator: ComparisonOperator)

fun QuestionCompare.isCorrect(answer: Boolean): Boolean {
    return operator.compare(num1, num2) == answer
}

object Compare {
    fun getQuestions(): Pair<String, Boolean> {
        val num1 = Random.nextInt(1, 101)
        val num2 = Random.nextInt(1, 101)
        val operator = if (Random.nextBoolean()) GreaterThan.GREATER_THAN else LessThan.LESS_THAN
        val problem = "$num1 ${operator.operation} $num2"

        return Pair(problem, operator.compare(num1, num2))
    }
}

fun main() {
    val question = Compare.getQuestions()
    println("Question: ${question.first}")
    // Simulating user input, correct answer is `true` if the first number is greater than the second
    val userAnswer = true
    val isCorrect = question.second == userAnswer
    println("Your answer is ${if (isCorrect) "correct" else "incorrect"}")
}

