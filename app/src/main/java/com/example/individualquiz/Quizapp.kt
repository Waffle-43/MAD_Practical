package com.example.individualquiz

import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class QuizApp : AppCompatActivity(){
    private val questionList = mutableListOf<Question>()
    private var score = 0

    fun startQuiz() {
        initializeQuestions()
        displayInstructions()
        askQuestions()
        displayResult()
        askForRetry()
    }

    private fun initializeQuestions() {
        for (i in 1..10) {
            val questionType = Random.nextInt(3)
            val question = when (questionType) {
                0 -> ComparingQuestion(Random.nextInt(1, 101), Random.nextInt(1, 101))
                1 -> OrderingQuestion(
                        Random.nextInt(1, 101),
                        Random.nextInt(1, 101),
                        Random.nextInt(1, 101)
                )
                else -> CompositionQuestion(
                        Random.nextInt(1, 101),
                        Random.nextInt(1, 101),
                        Random.nextInt(1, 101),
                        Random.nextInt(1, 101)
                )
            }
            questionList.add(question)
        }
    }

    private fun displayInstructions() {
        println("Welcome to the Quiz App!")
        println("You will be presented with 10 questions. Answer each question correctly to earn points.")
        println("Let's begin!\n")
    }

    private fun askQuestions() {
        for ((index, question) in questionList.withIndex()) {
            println("Question ${index + 1}: ${question.problem}")
            val userAnswer = readLine()?.toIntOrNull()
            if (userAnswer != null && question.isCorrect(userAnswer)) {
                println("Correct!")
                score++
            } else {
                println("Incorrect!")
            }
            println()
        }
    }

    private fun displayResult() {
        println("Quiz completed!")
        println("Your score is: $score / 10")
    }

    private fun askForRetry() {
        println("Do you want to retry? (yes/no)")
        val response = readLine()
        if (response?.toLowerCase() == "yes") {
            score = 0
            startQuiz()
        } else {
            println("Thank you for playing!")
        }
    }
}

fun main() {
    val quizApp = QuizApp()
    quizApp.startQuiz()
}

interface Question {
    val problem: String
    fun isCorrect(answer: Int): Boolean
}

data class ComparingQuestion(private val num1: Int, private val num2: Int) : Question {
    override val problem: String = "Which number is greater: $num1 or $num2?"
    override fun isCorrect(answer: Int): Boolean = if (num1 > num2) answer == num1 else answer == num2
}

data class OrderingQuestion(private val num1: Int, private val num2: Int, private val num3: Int) : Question {
    override val problem: String = "Arrange the numbers in ascending order: $num1, $num2, $num3"
    override fun isCorrect(answer: Int): Boolean = listOf(num1, num2, num3).sorted() == listOf(answer)
}

data class CompositionQuestion(
        private val num1: Int,
        private val num2: Int,
        private val num3: Int,
        private val sum: Int
) : Question {
    override val problem: String = "What is the result of adding $num1, $num2, and $num3?"
    override fun isCorrect(answer: Int): Boolean = answer == sum
}